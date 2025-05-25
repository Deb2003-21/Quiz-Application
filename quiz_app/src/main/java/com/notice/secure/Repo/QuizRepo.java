package com.notice.secure.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.notice.secure.model.Quiz;

public interface QuizRepo extends JpaRepository<Quiz, Integer> {

	 @Query("SELECT q.id FROM Quiz q WHERE q.title = :title")
	Integer findByTitle(@Param("title") String  p);

}
