package com.example.back_end.repository;

import com.example.back_end.model.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostLikeRepo extends JpaRepository<PostLike, Integer>{
    boolean existsByPostIdAndTravelerId(Integer id, String id1);
}
