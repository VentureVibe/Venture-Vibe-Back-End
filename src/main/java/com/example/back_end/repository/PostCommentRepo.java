package com.example.back_end.repository;

import com.example.back_end.model.PostComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostCommentRepo extends JpaRepository<PostComment, Integer> {
    List<PostComment> findByPostId(Integer postId);
}
