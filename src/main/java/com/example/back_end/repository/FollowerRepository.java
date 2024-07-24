package com.example.back_end.repository;

import com.example.back_end.model.Follower;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowerRepository extends JpaRepository<Follower, Long> {
}
