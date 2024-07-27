package com.example.back_end.repository;

import com.example.back_end.model.TravelDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TravelDateRepo  extends JpaRepository<TravelDate, Long>{
}
