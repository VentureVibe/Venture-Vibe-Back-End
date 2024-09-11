package com.example.back_end.repository;

import com.example.back_end.model.TravelDestination;
import com.example.back_end.model.TravelGuideServices;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TravelGuideServiceRepo extends JpaRepository<TravelGuideServices, Long> {
}
