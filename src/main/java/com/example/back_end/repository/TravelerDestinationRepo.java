package com.example.back_end.repository;

import com.example.back_end.model.TravelDestination;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TravelerDestinationRepo extends JpaRepository<TravelDestination, Long> {
}
