package com.example.back_end.repository;

import com.example.back_end.model.Follower;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowerRepository extends JpaRepository<Follower, Long> {
    boolean existsByTravelerIdAndFollowedTravelerId(String travelerId, String followedTravelerId);

    Iterable<Follower> findAllByTravelerId(String travelerId);

    Iterable<Follower> findAllByFollowedTravelerId(String followedTraveler);

    Follower findByTravelerIdAndFollowedTravelerId(String travelerId, String followedTravelerId);
}
