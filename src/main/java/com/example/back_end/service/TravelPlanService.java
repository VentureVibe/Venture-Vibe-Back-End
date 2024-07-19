package com.example.back_end.service;

import com.example.back_end.dto.TravelPlanDto;
import com.example.back_end.dto.TravelerDto;
import com.example.back_end.exception.allreadyexists.AllReadyExists;
import com.example.back_end.exception.deletefailed.DeleteFailed;
import com.example.back_end.exception.notfound.NotFound;
import com.example.back_end.exception.savefailed.SavedFailed;
import com.example.back_end.model.TravelPlan;
import com.example.back_end.model.Traveler;
import com.example.back_end.repository.TravelPlanRepo;
import com.example.back_end.repository.TravelerRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class TravelPlanService {

    @Autowired
    private final TravelPlanRepo travelPlanRepo;

    @Autowired
    private TravelerRepo travelerRepo;

    private final ModelMapper modelMapper;

    public TravelPlanDto getTravelerPlanByID(Long travelerPlanId){
        TravelPlan travelPlan= travelPlanRepo.findById(travelerPlanId)
                .orElseThrow(() -> new NotFound());
        return modelMapper.map(travelPlan, TravelPlanDto.class);
    }


    public List<TravelPlan> getTravelPlansByUserId(String userId) {

        Optional<Traveler> travelerOptional = travelerRepo.findById(userId);
        if (travelerOptional.isPresent()) {
            Traveler traveler = travelerOptional.get();
            return traveler.getTravelplans();
        } else {
            throw new RuntimeException("Traveler not found with id: " + userId);
        }
    }

    public TravelPlanDto addTravelPlan(String travelerId, TravelPlanDto travelPlanDto) {
        Traveler traveler;
        try {
            traveler = travelerRepo.findById(travelerId)
                    .orElseThrow(() -> new NotFound());


            TravelPlan travelPlanEntity = modelMapper.map(travelPlanDto, TravelPlan.class);

            if (travelPlanEntity.getTravelers() == null) {
                travelPlanEntity.setTravelers(new ArrayList<>());
            }

            travelPlanEntity.getTravelers().add(traveler);

            if (traveler.getTravelplans() == null) {
                traveler.setTravelplans(new ArrayList<>());
            }

            traveler.getTravelplans().add(travelPlanEntity);

            TravelPlan savedTravelPlan = travelPlanRepo.save(travelPlanEntity);

            return modelMapper.map(savedTravelPlan, TravelPlanDto.class);
        } catch (NotFound e) {
            throw new NotFound();
        }
        catch (Exception ee) {
            throw new SavedFailed();
        }
    }


    @Transactional
    public TravelPlanDto deleteTravelPlan(Long travelPlanId) {
        TravelPlan travelPlan = travelPlanRepo.findById(travelPlanId)
                .orElseThrow(() -> new NotFound());

        try{
            for (Traveler traveler : new ArrayList<>(travelPlan.getTravelers())) {
                traveler.getTravelplans().remove(travelPlan);
                travelerRepo.save(traveler); // Ensure changes are persisted
            }

            travelPlanRepo.delete(travelPlan);
            return modelMapper.map(travelPlan, TravelPlanDto.class);
        }
        catch(Exception e){
               throw new DeleteFailed();
        }


    }
}
