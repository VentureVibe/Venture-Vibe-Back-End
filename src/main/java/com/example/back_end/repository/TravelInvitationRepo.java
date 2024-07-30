package com.example.back_end.repository;

import com.example.back_end.model.TravelInvitation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TravelInvitationRepo extends JpaRepository<TravelInvitation, Long> {

    @Query(value = "SELECT * FROM travel_invitation WHERE travelplan_invitaion = :travelerId", nativeQuery = true)
    Page<TravelInvitation> findByTravelPlanInviteeId(String travelerId, Pageable pageable);
}
