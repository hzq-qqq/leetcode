package leetcode.xm.controller;

import leetcode.xm.domain.UserPerformance;
import leetcode.xm.pojo.Problem;
import leetcode.xm.pojo.Problemdesc;
import leetcode.xm.services.ProblemService;
import org.apache.catalina.mbeans.UserMBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ProBlenCtroller {

    @Autowired
    private ProblemService problemService;

    /**
     * 获取用户的做题情况
     * @param userId  用户id
     * @return
     */
    @ResponseBody
    @RequestMapping("/leetcode/question/user/{userId}")
    public UserPerformance findUserPerformance(@PathVariable("userId") Integer userId){
        return problemService.findUserPerFormance(userId);

    }
    /**
     * 分页展示数据
     * @param request 请求
     * @return
     */
    @RequestMapping("/leetcode/question/{page}/{userId}")
    @ResponseBody
    public List<Problem> findProblem(HttpServletRequest request, @PathVariable("page")Integer page, @PathVariable("userId") Integer userId){
        String problemKey = request.getParameter("problemKey");
        String limit = request.getParameter("limit");
        Integer limit1;
//        默认paseSize 长度为 50
        if (limit == null || "".equals(limit)){
            limit1 = 50;
        }
        else
        {
            limit1 = Integer.parseInt(limit);
        }
        String difficult = request.getParameter("difficult");
        String submitResult = request.getParameter("submitResult");
        return problemService.findProblem(page, userId, problemKey, limit1, difficult, submitResult);
    }

    /**
     * 根据题目id 展示题目的详细信息
     * @param problemId 题目id
     * @return
     */
    @RequestMapping("/leetcode/question/{randomId}")
    @ResponseBody
   public Problemdesc findProblemDexc(@PathVariable("randomId") Integer problemId){
        return problemService.findProblemDesc(problemId);
    }
}
