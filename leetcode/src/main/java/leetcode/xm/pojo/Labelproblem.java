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
public class Labelproblem {
    @TableField("`labelId`")
    private Integer labelId;
    @TableField("`problemId`")
    private Integer problemId;
}
