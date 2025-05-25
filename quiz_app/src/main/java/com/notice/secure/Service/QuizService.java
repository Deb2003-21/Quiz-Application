package com.notice.secure.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.notice.secure.Repo.QuedtionRepo;
import com.notice.secure.Repo.QuizRepo;
import com.notice.secure.model.Question;
import com.notice.secure.model.QuestionWrapper;
import com.notice.secure.model.Quiz;
import com.notice.secure.model.Response;

import jakarta.servlet.http.HttpSession;

@Service
public class QuizService {

	@Autowired
	QuedtionRepo qr;
	@Autowired
	QuizRepo qro;
	public String createQuiz(String category,String difficulty, int num, String title) {
		// TODO Auto-generated method stub
		Quiz quiz=new Quiz();
		if ("mix".equals(difficulty))
		{
			List<Question> qs= qr.findRandomQuesByCategory(category,num);
			quiz.setQuestions(qs);
		}
		else {
		List<Question> qs= qr.findRandomQuestionByCategory(category,num,difficulty);
		quiz.setQuestions(qs);
		}
		
		quiz.setTitle(title);
		
		qro.save(quiz);
		
		return "success";
		
	}
	public List<QuestionWrapper> getQuizQuestions(int id) {
		// TODO Auto-generated method stub
		Optional<Quiz> quiz= qro.findById(id);
		List<Question> quesfromdb=quiz.get().getQuestions();
		List<QuestionWrapper> quesforuser =new ArrayList<>();
		
		for (Question q: quesfromdb)
		{
			QuestionWrapper qw=new QuestionWrapper(q.getId(), q.getQuestion());
			quesforuser.add(qw);
		}
		return quesforuser;

	}
	public ResponseEntity<Integer> calculate(Integer id, List<Response> response) {
		// TODO Auto-generated method stub
		Quiz quiz = qro.findById(id).get();
		List<Question> qst=quiz.getQuestions();
		int r1=0,i=0;
		for (Response r:response)
		{
			if(r.getResponse().equals(qst.get(i).getCorrect_answer()))
			{
				r1++;
			}
			i++;
		}
		return new ResponseEntity<>(r1,HttpStatus.OK);
	}
	public Integer getId(String p) {
		// TODO Auto-generated method stub
		
		return qro.findByTitle(p);
		
	}

	
}
