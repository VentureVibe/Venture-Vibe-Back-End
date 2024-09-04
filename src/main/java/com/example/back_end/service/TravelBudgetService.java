package com.example.back_end.service;

import com.example.back_end.exception.notfound.NotFound;
import com.example.back_end.exception.savefailed.SavedFailed;
import com.example.back_end.model.*;
import com.example.back_end.repository.TravelBudgetRepo;
import com.example.back_end.repository.TravelPlanRepo;
import com.example.back_end.repository.TravelerDestinationRepo;
import com.example.back_end.repository.TravelerRepo;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TravelBudgetService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private TravelBudgetRepo travelBudgetRepo;


    @Autowired
    private TravelPlanRepo travelPlanRepo;

    @Autowired
    private TravelerDestinationRepo travelerDestinationRepo;

    @Transactional
    public TravelBudget addTravelBudget(TravelBudget travelBudget,Long travelPlanId) {

        TravelPlan travelPlan = travelPlanRepo.findById(travelPlanId)
                .orElseThrow(() -> new NotFound())
                ;
        travelBudget.setTravelPlan(travelPlan);

        TravelBudget savedTravelBudget = travelBudgetRepo.save(travelBudget);

        return savedTravelBudget;
    }

    @Transactional
    public TravelBudget addTravelBudgetDestination(TravelBudget travelBudget, Long travelPlanId, Long travelDestinationId) {

        TravelPlan travelPlan = travelPlanRepo.findById(travelPlanId)
                .orElseThrow(() -> new NotFound());

        travelBudget.setTravelPlan(travelPlan);

        if (travelDestinationId != null) {
            TravelDestination travelDestination = travelerDestinationRepo.findById(travelDestinationId)
                    .orElseThrow(() -> new NotFound());

            travelBudget.setTravelDestination(travelDestination);

            travelDestination.setTravelBudget(travelBudget);
        }

        TravelBudget savedTravelBudget = travelBudgetRepo.save(travelBudget);

        if (travelDestinationId != null) {
            travelerDestinationRepo.save(travelBudget.getTravelDestination());
        }

        return savedTravelBudget;
    }


    @Transactional
    public TravelBudget updateTravelBudget(TravelBudget travelBudget,Long travelPlanId) {

        TravelPlan travelPlan = travelPlanRepo.findById(travelPlanId)
                .orElseThrow(() -> new NotFound())
                ;
        travelBudget.setTravelPlan(travelPlan);

        TravelBudget savedTravelBudget = travelBudgetRepo.save(travelBudget);

        return savedTravelBudget;
    }

    public List<TravelBudget> getTravelBudgetsByTravelPlanId(Long travelPlanId) {
        TravelPlan travelPlan = travelPlanRepo.findById(travelPlanId)
                .orElseThrow(() -> new NotFound());

        return travelBudgetRepo.findByTravelPlanId(travelPlanId);
    }

    @Transactional
    public void deleteTravelBudget(Long travelPlanId) {

        TravelBudget travelBudget = travelBudgetRepo.findById(travelPlanId)
                .orElseThrow(() -> new NotFound());

        travelBudgetRepo.delete(travelBudget);
    }


}
