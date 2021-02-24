package leetcode.xm.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserPerformance {
    private Integer totalProblem;//所有题目数量
    private Integer finishedProblem;//完成了的题目数量，
    private Integer countEasy; // 简单  1
    private Integer countMid; // 中等   2
    private Integer countHard; // 困难  3
}
