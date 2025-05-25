package com.notice.secure.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.notice.secure.model.Question;

@Repository
public interface QuedtionRepo extends JpaRepository<Question, Integer>{

	List<Question> getQuestionByCategory(String c);

	@Query(value = "SELECT * FROM questions q WHERE q.category = :category and q.difficulty =:difficulty ORDER BY RAND() LIMIT :num;",nativeQuery = true)
	List<Question> findRandomQuestionByCategory(String category,int num, String difficulty);

	@Query(value = "SELECT * FROM questions q WHERE q.category = :category  ORDER BY RAND() LIMIT :num;",nativeQuery = true)
	List<Question> findRandomQuesByCategory(String category, int num);

}
