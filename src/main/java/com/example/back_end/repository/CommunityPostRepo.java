package com.example.back_end.repository;

import com.example.back_end.model.CommunityPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommunityPostRepo extends JpaRepository<CommunityPost, Integer> {

}
