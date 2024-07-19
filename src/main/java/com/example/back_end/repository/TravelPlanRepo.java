package com.example.back_end.repository;

import com.example.back_end.model.TravelPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TravelPlanRepo extends JpaRepository<TravelPlan,Long> {
}
