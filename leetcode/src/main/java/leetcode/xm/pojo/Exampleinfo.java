package leetcode.xm.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *题目的详细详细信息之实例内容
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Exampleinfo {
     @TableField("`exampleId`")
     private Integer exampleId; // 示例id

     @TableField("`exampleInput`")
     private String exampleInput; //示例内容输入

     @TableField("`exampleOutput`")
     private String exampleOutput;//示例内容输出

     @TableField("`exampleExplain`")
     private String exampleExplain;//示例解释

     @TableField("`exampleImage`")
     private String exampleImage;//示例图片

     @TableField("`problemId`")
     private Integer problemId; // 题目id
}
