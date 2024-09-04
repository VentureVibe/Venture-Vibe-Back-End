package com.example.back_end.controller;

import com.example.back_end.dto.TravelDestinationDTO;
import com.example.back_end.dto.TravelPlanDto;
import com.example.back_end.model.TravelDestination;
import com.example.back_end.service.TravelDestinationService;
import com.example.back_end.service.TravelPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/api/v1/traveldestination")
public class TravelDestinationController {

    @Autowired
    private TravelDestinationService travelerDestinationService;

    @GetMapping(value = "/{travelDestinationId}")
    public ResponseEntity<TravelDestinationDTO> createTravelPlan(@PathVariable Long travelDestinationId) {

        TravelDestinationDTO travelDestination1 = travelerDestinationService.getTravelDestination(travelDestinationId);
        return new ResponseEntity<>(travelDestination1, HttpStatus.CREATED);
    }

    @PostMapping(value = "/{travelPlanId}")
    public ResponseEntity<TravelPlanDto> createTravelPlan(@PathVariable Long travelPlanId, @RequestBody TravelDestination travelDestination) {
      
        TravelPlanDto createdTravelPlan = travelerDestinationService.addTravelDestination(travelPlanId, travelDestination);
        return new ResponseEntity<>(createdTravelPlan, HttpStatus.CREATED);
    }

    @PutMapping(value = "")
    public ResponseEntity<TravelDestination> updateTravelPlan(@RequestBody TravelDestination travelDestination) {

        TravelDestination travelDestination1 = travelerDestinationService.updateTravelDestination(travelDestination);
        return new ResponseEntity<>( travelDestination1 , HttpStatus.CREATED);
    }


    @DeleteMapping(value = "/{travelDestinationId}")
    public ResponseEntity<TravelDestinationDTO> deleteTravelDestination(@PathVariable Long travelDestinationId) {
        TravelDestinationDTO travelDestinationDTO = travelerDestinationService.deleteTravelDestination(travelDestinationId);
        return new ResponseEntity<>(travelDestinationDTO, HttpStatus.OK); // Use HttpStatus.OK for successful deletion
    }


}
