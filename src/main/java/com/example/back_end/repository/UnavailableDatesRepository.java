package com.example.back_end.repository;

import com.example.back_end.model.UnavailableDates;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnavailableDatesRepository extends JpaRepository<UnavailableDates, Long> {
    UnavailableDates findByUserId(String userId);
}
