package com.example.back_end.repository;

import com.example.back_end.model.TravelBudget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TravelBudgetRepo extends JpaRepository<TravelBudget, Long> {
    List<TravelBudget> findByTravelPlanId(Long travelPlanId);
}
