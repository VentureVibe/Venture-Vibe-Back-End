package com.example.back_end.repository;

import com.example.back_end.model.Traveler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TravelerRepo extends JpaRepository<Traveler,String> {
    Traveler findByEmail(String email);
}
