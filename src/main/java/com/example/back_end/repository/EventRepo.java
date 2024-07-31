package com.example.back_end.repository;

import com.example.back_end.model.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepo extends JpaRepository<Event, Integer> {
    Page<Event> findAll(Pageable pageable);
    Page<Event> findByUserId(String userId, Pageable pageable);
}
