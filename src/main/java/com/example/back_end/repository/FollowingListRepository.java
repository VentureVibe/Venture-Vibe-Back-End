package com.example.back_end.repository;

import com.example.back_end.model.FollowingList;
import com.example.back_end.model.Traveler;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FollowingListRepository extends JpaRepository<FollowingList, Long> {
    //Optional<FollowingList> findByTravelerId(Long travelerId);

    Optional<FollowingList> findByTraveler(Traveler traveler);
}
