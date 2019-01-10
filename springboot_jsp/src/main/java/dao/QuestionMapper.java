package dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import model.Question;

@Mapper
public interface QuestionMapper {

	Integer insert(Question question);
	
	Integer insertBatch(@Param("list") List<Question> list);
}
