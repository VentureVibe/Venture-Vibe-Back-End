package com.example.back_end.repository;

import com.example.back_end.model.CommunityPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommunityPostRepo extends JpaRepository<CommunityPost, Integer> {

    List<CommunityPost> findByUserId(String userId);

    // Method using Spring Data JPA's method naming convention
    List<CommunityPost> findByContentContainingIgnoreCase(String city);

    // Method using a custom query
    @Query("SELECT cp FROM CommunityPost cp WHERE LOWER(cp.content) LIKE LOWER(CONCAT('%', :city, '%'))")
    List<CommunityPost> findPostsByCityInContent(@Param("city") String city);
}
