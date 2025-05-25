package com.notice.secure.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.notice.secure.model.LeaderBoard;

public interface LeadRepo extends JpaRepository<LeaderBoard, String>{

}
