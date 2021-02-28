package leetcode.xm.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Problemdesc {
    @TableField("`problemId`")
    private Integer problemId; //题目id

    @TableField("`problemName`")
    private String problemName; //题目名称

    @TableField("`difficulty`")
    private Integer difficulty; //题目难度

    @TableField("`problemLikeNumber`")
    private Integer problemLikeNumber; //点赞数

    @TableField("`problemContent`")
    private String problemContent ; // 题目内容

    @TableField("`tipsInfo`")
    private String tipsInfo ; //提示信息

    @TableField("`passNumber`")
    private Integer passNumber; //通过次数

    @TableField("`submitNumber`")
    private Integer submitNumber; // 提交次数

    @TableField("`problemexample`")
    private Exampleinfo problemexample;// 示例内容

    @TableField("`labelList`")
    private List<Label> labelList ; // 题目标签


}
