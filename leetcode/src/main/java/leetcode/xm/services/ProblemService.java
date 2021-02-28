package leetcode.xm.services;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import leetcode.xm.domain.UserPerformance;
import leetcode.xm.mapper.*;
import leetcode.xm.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;


@Service
public class ProblemService {
    @Autowired
    private ProblemMapper pMapper;
    @Autowired
    private ProblemRecordMapper pRMapper;
    @Autowired
    private ProblemDescMapper problemDescMapper;
    @Autowired
    private ExampleinfoMapper exampleinfoMapper;
    @Autowired
    private LabelMapper labelMapper;
    @Autowired
    private LabelproblemMapper labelproblemMapper;

   /**
     * 查找题目分页展示
     * @param page 分页的页码
     * @param userId 用户id id可以为null
     * @param problemKey 搜索关键字 可以为null
     * @param limit 每页题目的数量   可以为null
     * @param difficulty 根据难度搜索题目 可以为 null
     * @param submitResult 根据题目的做题记录搜索题目 可以为null
     * @return
     */
    public List<Problem> findProblem(@RequestParam("page") Integer page, @RequestParam("userId")Integer userId , @RequestParam("problemKey")String problemKey, @RequestParam("limit")Integer limit, @RequestParam("difficulty")String difficulty, @RequestParam("submitresult")String submitResult){
        QueryWrapper<Problem> pWrapper = new QueryWrapper<>();
        List<Problem> problemList = new ArrayList<>();
            if (problemKey != null){
                pWrapper.like("problemName",problemKey);
            }
            if (difficulty != null){
                pWrapper.eq("difficulty",difficulty);
            }
            if (submitResult != null){
                if (userId != null) {
//               由于用户登录了 所以查询的是用户当前条件的做题记录
                    QueryWrapper<Problemrecord> pRwrapper = new QueryWrapper<>();
//               获取用户当前条件的所有的做题记录，
                    pRwrapper.eq("userId", userId)
                            .eq("problemRecord", submitResult);
                    List<Problemrecord> recordList = pRMapper.selectPage(new Page<>(page, limit), pRwrapper).getRecords();
//                    根据做题记录去查相应的题的信息，并设置做题记录
                    for (Problemrecord problemrecord : recordList) {
                        QueryWrapper<Problem> wrapper = new QueryWrapper<>();
                        Integer problemId = problemrecord.getProblemId();
                        Integer problemRecord = problemrecord.getProblemRecord();
                        wrapper.eq("problemId", problemId);
//                        这里考虑优化，考虑如何实现只于数据库建立一次链接
                        Problem problem = pMapper.selectOne(wrapper);
//                        添加数据
                        problem.setSubmitResult(problemRecord);
                        problemList.add(problem);
                    }
                }
                    //           用户未登录，将所有题目的做题记录修改为 未做 将record 置为3
                else {
                    Page<Problem> problemPage1 = pMapper.selectPage(new Page<>(page, limit), pWrapper);
                    List<Problem> problems = problemPage1.getRecords();  // 查询当前页的题的数据
                        for (Problem problem : problems) {
                            problem.setSubmitResult(3);               }
                    }
                    return problemList;
                }
//            未设置提交记录,就按照其他条件查询数据，也需要返回用户的做题记录
        List<Problem> problemList1 = pMapper.selectPage(new Page<>(page, limit), pWrapper).getRecords();
        if (userId == null) {
            for (Problem problem : problemList1) {
                problem.setSubmitResult(3);
            }
            return problemList1;
        }
        else{
            //            查询用户的做题记录
            for (Problem problem : problemList1) {
                QueryWrapper<Problemrecord> problemrecordQueryWrapper = new QueryWrapper<>();
                int problemId = problem.getProblemId();
                problemrecordQueryWrapper.eq("userId",userId)
                        .eq("problemId",problemId);
                Problemrecord problemrecord = pRMapper.selectOne(problemrecordQueryWrapper);
                problem.setSubmitResult(problemrecord.getProblemRecord());
            }
        }
        return problemList1;
    }

    /**
     * 获取用户的做题情况
     * @param userId  用户 id
     * @return
     */
    public UserPerformance findUserPerFormance(Integer userId) {

//        考虑使用多线程的方式实现查询
//        获取所有的题目数量
        Integer allProblem = pMapper.selectCount(null);
//        获取用户完成的题目数量
        QueryWrapper<Problemrecord> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userId",userId)
                .eq("problemRecord","2");
        Integer userFinish = pRMapper.selectCount(queryWrapper);

//        获取用户完成题目中简单,中等，困难的难度的题目数量
        Integer easy = 0;
        int mid = 0;
        int tru = 0;
        QueryWrapper<Problemrecord> wrapper1= new QueryWrapper<>();
        wrapper1.eq("problemRecord","2")
                .eq("userId",userId);
        List<Problemrecord> problemrecords = pRMapper.selectList(wrapper1);
        for (Problemrecord problemrecord : problemrecords) {
            Integer problemId = problemrecord.getProblemId();
            QueryWrapper<Problem> problemQueryWrapper = new QueryWrapper<>();
            problemQueryWrapper.eq("problemId", problemId);
            Problem problem = pMapper.selectOne(problemQueryWrapper);
            if ("1".equals(problem.getDifficulty())){
                easy++;
            }
            if ("2".equals(problem.getDifficulty())){
                mid++;
            }
            if ("3".equals(problem.getDifficulty())){
                tru++;
            }
        }

//        获取用户完成题目中中等难度的题目

           return new UserPerformance(allProblem, userFinish, easy, mid, tru);


    }

    /**
     * 根据题目id 展示题目的详细信息
     * @param problemId 题目id
     * @return
     */
    public Problemdesc findProblemDesc(Integer problemId) {
//        考虑使用多线程来实现
//        如果 problemId 为0 表示随机开始一道题目
        if (problemId == 0){
            Integer randomId = pMapper.selectCount(null);
            problemId = (int) (Math.random() * randomId) + 1;
        }
//     获取题目的详细信息
        QueryWrapper<Problemdesc> pdQwrapper = new QueryWrapper<>();
        pdQwrapper.eq("problemId",problemId);
        Problemdesc problemdesc = problemDescMapper.selectOne(pdQwrapper);

//        根据题目id获取题目的示例信息
        QueryWrapper<Exampleinfo> eqw = new QueryWrapper<>();
         eqw.eq("problemId", problemId);
        Exampleinfo exampleinfo = exampleinfoMapper.selectOne(eqw);

//        根据题目id获取题目的label信息
//        创建一个labellist
          List<Label>labelList = new ArrayList<>();
//        1.根据 problemId  获取 labelId
        QueryWrapper<Labelproblem> lpw = new QueryWrapper<>();
        lpw.eq("problemId", problemId);
        List<Labelproblem> labelproblemIds = labelproblemMapper.selectList(lpw);
//        2.根据labelId 获取 label信息
        for (Labelproblem labelproblemId : labelproblemIds) {
            QueryWrapper<Label> lw = new QueryWrapper<>();
            lw.eq("labelId", labelproblemId.getLabelId());
            Label label = labelMapper.selectOne(lw);
//            存入lebelList
            labelList.add(label);
        }

//        将example和label 存入problemdesc
        problemdesc.setProblemexample(exampleinfo);
        problemdesc.setLabelList(labelList);
        return problemdesc;
    }
}
