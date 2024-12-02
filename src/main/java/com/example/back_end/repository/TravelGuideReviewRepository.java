package com.example.back_end.repository;

import com.example.back_end.model.TravelGuideReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TravelGuideReviewRepository extends JpaRepository<TravelGuideReview, Long> {
    List<TravelGuideReview> findByTravelGuideId(String travelGuideId);
}
