package com.example.back_end.controller;

import com.example.back_end.dto.TravelPlanDto;

import com.example.back_end.exception.notfound.NotFound;
import com.example.back_end.service.TravelPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping(value="/api/v1/travelplan")


public class TravelPlanController {

    @Autowired
    private TravelPlanService travelerPlanService;


    @GetMapping("/{travelerPlanId}/{travelerId}")
    public ResponseEntity<TravelPlanDto> getTraveler(
            @PathVariable Long travelerPlanId,
            @PathVariable String travelerId) {
        return new ResponseEntity<>(travelerPlanService.getTravelerPlanByID(travelerPlanId, travelerId), HttpStatus.OK);
    }



    @GetMapping("/accepted/{travelerId}")
    public ResponseEntity<Page<TravelPlanDto>> getAcceptedTravelPlansByUserId(@PathVariable String travelerId,
                                                                              @RequestParam(value = "page", defaultValue = "0") int page,
                                                                              @RequestParam(value = "size", defaultValue = "10") int size){
        Page<TravelPlanDto> travelPlanPage=travelerPlanService.getAcceptedTravelPlansByUserId(travelerId,page,size);
        return new ResponseEntity<>(travelPlanPage, HttpStatus.OK);
    }


    @GetMapping("/owned/{travelerId}")
    public ResponseEntity<Page<TravelPlanDto>> getOwnedTravelPlansByUserId(@PathVariable String travelerId,
                                                                           @RequestParam(value = "page", defaultValue = "0") int page,
                                                                           @RequestParam(value = "size", defaultValue = "10") int size){
        return new ResponseEntity<>(travelerPlanService.getOwnedTravelPlansByUserId(travelerId,page,size), HttpStatus.CREATED);
    }


    @GetMapping("/traveler/{travelerId}")
    public ResponseEntity<List<TravelPlanDto>> getTravelPLanBy(@PathVariable String travelerId){
        return new ResponseEntity<>(travelerPlanService.getTravelPlansByUserId(travelerId), HttpStatus.CREATED);
    }


    @PostMapping(value = "/{travelerId}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<TravelPlanDto> createTravelPlan(@PathVariable String travelerId, @RequestBody TravelPlanDto travelPlan) {
        TravelPlanDto createdTravelPlan = travelerPlanService.addTravelPlan(travelerId, travelPlan);
        return new ResponseEntity<>(createdTravelPlan, HttpStatus.CREATED);
    }



    @DeleteMapping("/{travelPlanId}")
    public ResponseEntity<TravelPlanDto> deleteTravelPlan(@PathVariable Long travelPlanId) {
        TravelPlanDto deletedTravelPlan = travelerPlanService.deleteTravelPlan(travelPlanId);
        return new ResponseEntity<>(deletedTravelPlan, HttpStatus.CREATED);
    }

    @DeleteMapping("/{travelPlanId}/{travelerId}")
    public ResponseEntity<TravelPlanDto> deleteTravelerFromTravelPlan(@PathVariable Long travelPlanId,@PathVariable String travelerId){
        TravelPlanDto deletedTravelPlan = travelerPlanService.deleteTravelerFromTravelPlan(travelPlanId,travelerId);
        return new ResponseEntity<>(deletedTravelPlan, HttpStatus.CREATED);

    }

    @PutMapping("/{travelPlanId}/{travelerId}")
    public ResponseEntity<TravelPlanDto> addTravelerToTravelPlan(
            @PathVariable Long travelPlanId,
            @PathVariable String travelerId) {

        try {
            TravelPlanDto updatedTravelPlan = travelerPlanService.addTravelerToTravelPlan(travelPlanId, travelerId);
            return new ResponseEntity<>(updatedTravelPlan, HttpStatus.OK);
        } catch (NotFound e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
