package com.example.back_end.repository;

import com.example.back_end.model.CommunityPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommunityPostRepo extends JpaRepository<CommunityPost, Integer> {

    List<CommunityPost> findByUserId(String userId);
}
