package com.example.back_end.service;

import com.example.back_end.dto.TravelDestinationDTO;
import com.example.back_end.dto.TravelPlanDto;
import com.example.back_end.exception.notfound.NotFound;
import com.example.back_end.model.*;
import com.example.back_end.repository.TravelPlanRepo;
import com.example.back_end.repository.TravelerDestinationRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLOutput;

@Service
public class TravelDestinationService {

    @Autowired
    private TravelerDestinationRepo travelDestinationRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private TravelPlanRepo travelPlanRepo;

    @Transactional
    public TravelPlanDto addTravelDestination(Long travelPlanId, TravelDestination travelDestination) {

        TravelPlan travelPlan = travelPlanRepo.findById(travelPlanId)
                .orElseThrow(() -> new NotFound())
                ;
        travelDestination.setTravelPlan(travelPlan);
        travelDestinationRepo.save(travelDestination);

        TravelPlanDto travelPlanDto = modelMapper.map(travelPlan, TravelPlanDto.class);
        return travelPlanDto;
    }

    @Transactional
    public TravelDestination updateTravelDestination( TravelDestination travelDestination) {



        TravelDestination travelDestination1=travelDestinationRepo.save(travelDestination);

        return travelDestination1;
    }

    @Transactional
    public TravelDestinationDTO getTravelDestination(Long travelDestinationId) {

        TravelDestination travelDestination = travelDestinationRepo.findById(travelDestinationId)
                .orElseThrow(() -> new NotFound());

        TravelDestinationDTO travelDestinationDTO = modelMapper.map(travelDestination, TravelDestinationDTO.class);

        return travelDestinationDTO;
    }

    @Transactional
    public TravelDestinationDTO deleteTravelDestination(Long travelDestinationId) {
        // Fetch the entity before deleting
        TravelDestination travelDestination = travelDestinationRepo.findById(travelDestinationId)
                .orElseThrow(() -> new NotFound());

        // Map to DTO
        TravelDestinationDTO travelDestinationDTO = modelMapper.map(travelDestination, TravelDestinationDTO.class);
        travelDestinationRepo.deleteById(travelDestinationId);



        // Return the DTO
        return travelDestinationDTO;
    }


}
