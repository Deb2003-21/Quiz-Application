package com.notice.secure;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.notice.secure.Service.QuizService;
import com.notice.secure.model.QuestionWrapper;
import com.notice.secure.model.Response;

import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;



@Controller
@RequestMapping("quiz")
public class QuizCcontroller {

	@Autowired
	QuizService qs;
	@PostMapping("create")
	public String createquiz(@RequestParam String category,@RequestParam String difficulty,@RequestParam int num,HttpSession hs)
	{
		String title=(String) hs.getAttribute("player");
		String s=qs.createQuiz(category,difficulty,num,title);
		if("success".equals(s)==true)
		{
			
			return "redirect:/quiz/get";
		}
		return "error";
	}
	@GetMapping("get")
	public  String getquiz(Model m,HttpSession hs)
	{
		 String p=(String) hs.getAttribute("player");
		 Integer id= qs.getId(p);
		 List<QuestionWrapper> l = qs.getQuizQuestions(id);
		 m.addAttribute("mid", id);
		 m.addAttribute("data", l);
		 
		 return "second";
	}
	
	@PostMapping("submit/{id}")
	public ResponseEntity<Integer> subquiz(@PathVariable Integer id,@RequestBody List<Response> response )
	{
		return qs.calculate(id,response);
	}
	
}
