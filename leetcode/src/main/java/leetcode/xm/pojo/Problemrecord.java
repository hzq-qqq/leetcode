package leetcode.xm.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Problemrecord {
    @TableField("`userId`")
    private Integer userId;// 用户id
    @TableField("`problemId`")
    private Integer problemId; //题目id
    @TableField("`problemRecord`")
    private Integer problemRecord; // 做题记录
}
