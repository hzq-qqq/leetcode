package leetcode.xm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import leetcode.xm.pojo.Problem;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProblemMapper extends BaseMapper<Problem> {
}

