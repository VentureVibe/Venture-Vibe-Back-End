package com.example.back_end.service;

import com.example.back_end.dto.TravelInviteDTO;
import com.example.back_end.dto.TravelPlanDto;
import com.example.back_end.dto.TravelerDto;
import com.example.back_end.exception.allreadyexists.AllReadyExists;
import com.example.back_end.exception.notfound.NotFound;
import com.example.back_end.exception.savefailed.SavedFailed;
import com.example.back_end.model.TravelInvitation;
import com.example.back_end.model.TravelPlan;
import com.example.back_end.model.Traveler;
import com.example.back_end.repository.TravelInvitationRepo;
import com.example.back_end.repository.TravelPlanRepo;
import com.example.back_end.repository.TravelerRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class TravelInvitationService {

    @Autowired
    private TravelInvitationRepo travelInvitationRepo;

    @Autowired
    private TravelPlanRepo travelPlanRepo;

    @Autowired
    private TravelerRepo travelerRepo;

    private final ModelMapper modelMapper;


    @Transactional
    public TravelInvitation addTravelInvitation(Long travelPlanId, String travelerId) {

        Optional<TravelPlan> travelPlanOptional = travelPlanRepo.findById(travelPlanId);
        if (!travelPlanOptional.isPresent()) {
            throw new NotFound();
        }
        TravelPlan travelPlan = travelPlanOptional.get();


        Optional<Traveler> travelerOptional = travelerRepo.findById(travelerId);
        if (!travelerOptional.isPresent()) {
            throw new NotFound();
        }
        Traveler traveler = travelerOptional.get();

        TravelInvitation travelInvitation = new TravelInvitation();
        travelInvitation.setTravelPlan(travelPlan);
        travelInvitation.setTravelPlanInvitee(traveler);

        try {
            TravelInvitation savedTravelInvitation = travelInvitationRepo.save(travelInvitation);
            return savedTravelInvitation;
        } catch (Exception e) {
            throw new SavedFailed();
        }
    }


    public TravelInviteDTO getTravelInvitationById(Long travelInvitationId) {
        TravelInvitation travelInvitation=travelInvitationRepo.findById(travelInvitationId)
                .orElseThrow(() -> new NotFound());
        TravelInviteDTO dto = modelMapper.map(travelInvitation, TravelInviteDTO.class);
        return dto;
    }



    public Page<TravelInviteDTO> getTravelInvitationsByTravelerId(String travelerId, int page, int size) {
        // Create a PageRequest with descending sort order by date
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(Sort.Order.desc("date")));

        // Fetch the page of TravelInvitation entities
        Page<TravelInvitation> travelInvitationPage = travelInvitationRepo.findByTravelPlanInviteeId(travelerId, pageRequest);

        // Map the entities to DTOs and include travelPlan details
        return travelInvitationPage.map(travelInvitation -> {
            TravelInviteDTO dto = modelMapper.map(travelInvitation, TravelInviteDTO.class);
            dto.setTravelPlan(travelInvitation.getTravelPlan()); // Assuming TravelInviteDTO has a setTravelPlan method
            return dto;
        });
    }



    @Transactional
    public TravelInviteDTO deleteTravelInvitationById(Long travelInvitationId) {
        TravelInvitation travelInvitation = travelInvitationRepo.findById(travelInvitationId)
                .orElseThrow(() -> new NotFound());

        try {
            travelInvitationRepo.delete(travelInvitation);
            return modelMapper.map(travelInvitation, TravelInviteDTO.class);
        } catch (Exception e) {
            throw new SavedFailed();
        }
    }





}
