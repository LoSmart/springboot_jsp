package service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.AnswerMapper;
import dao.QuestionMapper;
import model.Answer;
import model.Question;
import util.FileUpload;

@Service
public class QuestionService {

	@Autowired
	private QuestionMapper questionMapper;
	
	@Autowired
	private AnswerMapper answerMapper;
	
	@Autowired
	private FileUpload fileUpload;
	
	@Transactional
	public boolean insertQuestion(Question question) {
		Date date = new Date();
		question.setpId(0l);
		question.setCreateTime(date);
		question.setImageUrl(fileUpload.GenerateImage(question.getImageUrl()));
		questionMapper.insert(question);
		if(3==question.getType()) {
			List<Question> questions = question.getQuestions();
			for (Question question2 : questions) {
				question2.setpId(question.getId());
				question2.setCreateTime(date);
				question2.setImageUrl(fileUpload.GenerateImage(question2.getImageUrl()));
				questionMapper.insert(question2);
				List<Answer> answers = question2.getAnswers();
				answers.get(0).setQuestionId(question2.getId());
				answers.get(0).setCreateTime(date);
				answers.get(0).setImageUrl(fileUpload.GenerateImage(answers.get(0).getImageUrl()));
				answerMapper.insert(answers.get(0));
			}
		}else {
			List<Answer> answers = question.getAnswers();
			for(Answer answer : answers) {
				answer.setQuestionId(question.getId());
				answer.setImageUrl(fileUpload.GenerateImage(answer.getImageUrl()));
				answer.setCreateTime(date);
			}
			answerMapper.insertBatch(answers);
		}
		return true;
	}
	
	
}
