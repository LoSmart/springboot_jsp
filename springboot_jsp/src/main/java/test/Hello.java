package test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import model.Question;
import service.QuestionService;

/**
 * Created by daiwe on 2017/7/3.
 */
@RestController
public class Hello {

	@Autowired
	private QuestionService questionService;
	
    @RequestMapping("/hello")
    public ModelAndView hello(Model model, @RequestParam(value="name", required=false, defaultValue="World") String name) {
        model.addAttribute("name", name);
        return new ModelAndView("hello");
    }
    
    @PostMapping("/insert")
    public String insertQuestion(@RequestBody Question question) {
    	System.out.println(question.toString());
    	questionService.insertQuestion(question);
        return "hello";
    }
}
