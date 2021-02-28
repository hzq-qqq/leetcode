package leetcode.xm;

import leetcode.xm.domain.UserPerformance;
import leetcode.xm.pojo.Problem;
import leetcode.xm.pojo.Problemdesc;
import leetcode.xm.services.ProblemService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest()
class DemoApplicationTests {

	@Autowired
	private ProblemService service;
	@Test
   void findProblemTest(){

        System.out.println("test start");
        List<Problem> problem = service.findProblem(1, 1, null, 50, "1", null);
        for (Problem problem1 : problem) {
            System.out.println(problem1);
        }
	}
    @Test
    void performanceTest(){

        UserPerformance userPerFormance = service.findUserPerFormance(1);
        System.out.println(userPerFormance);
    }

    @Test
    void findProblemDesc(){
        Problemdesc problemDesc = service.findProblemDesc(0);
        System.out.println(problemDesc);
    }
}
