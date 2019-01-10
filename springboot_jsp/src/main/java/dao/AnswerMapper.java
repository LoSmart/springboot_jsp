package dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import model.Answer;

@Mapper
public interface AnswerMapper {

	Integer insertBatch(@Param("list") List<Answer> list);
	
	Integer insert(Answer answer);
}
