package com.example.back_end.repository;

import com.example.back_end.model.TravelGuide;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TravelGuideRepository extends JpaRepository<TravelGuide, String> {
}
