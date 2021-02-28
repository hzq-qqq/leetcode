package leetcode.xm.pojo;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Label {
    @TableField("`labelId`")
    private Integer labelId;// 标签id
    @TableField("`labelName`")
    private String labelName ; //标签名
}
