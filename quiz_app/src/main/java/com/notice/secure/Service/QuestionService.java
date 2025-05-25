package com.notice.secure.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.notice.secure.Repo.QuedtionRepo;
import com.notice.secure.model.Question;

@Service
public class QuestionService {

	@Autowired
	QuedtionRepo qsr;
	public  ResponseEntity<List<Question>> getall() {
		// TODO Auto-generated method stub
		try {
		return new ResponseEntity<>(qsr.findAll(),HttpStatus.OK);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
		
	}
	public List<Question> getQuestionByCategory(String c) {
		// TODO Auto-generated method stub
		return qsr.getQuestionByCategory(c);
	}
	public ResponseEntity<String> addQuestion(Question q) {
		// TODO Auto-generated method stub
		try {
		 qsr.save(q);
		 return new ResponseEntity<>("success",HttpStatus.CREATED);
		}
		catch(Exception e)
		{
			e.getStackTrace();		
		}
		return new ResponseEntity<>("failed",HttpStatus.BAD_REQUEST);

		 
	}

}
