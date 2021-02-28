package leetcode.xm.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer userId; // 用户id
    private String username; // 用户名
    private String password;// 密码
    private String phone; //电话号
    private String email; //邮箱
    private String image; //头像
}
