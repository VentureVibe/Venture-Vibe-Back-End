package com.example.back_end.repository;

import com.example.back_end.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByBookerId(String bookerId);
    List<Booking> findByTravelGuideId(String travelGuideId);
}