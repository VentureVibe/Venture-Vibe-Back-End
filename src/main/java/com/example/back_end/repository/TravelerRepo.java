package com.example.back_end.repository;

import com.example.back_end.model.Traveler;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TravelerRepo extends JpaRepository<Traveler,String> {


}
