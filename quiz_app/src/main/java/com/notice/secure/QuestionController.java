package com.notice.secure;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.notice.secure.Repo.LeadRepo;
import com.notice.secure.Service.QuestionService;
import com.notice.secure.model.LeaderBoard;
import com.notice.secure.model.Question;

@RestController
@RequestMapping("question")
public class QuestionController {

	@Autowired
	QuestionService qs;
	@Autowired
	LeadRepo lr;
	@GetMapping("allques")
	public ResponseEntity<List<Question>> getallques()
	{
		return qs.getall();
	}
	@GetMapping("category/{c}")
	public List<Question> getquesct(@PathVariable String c)
	{
		return qs.getQuestionByCategory(c);
	}
	
	@PostMapping("add")
	public ResponseEntity<String> addQuestion(@RequestBody Question q)
	{
		return qs.addQuestion(q);
	}
	
}
