package com.example.back_end.controller;

import com.example.back_end.dto.TravelInviteDTO;
import com.example.back_end.dto.TravelPlanDto;
import com.example.back_end.dto.TravelerDto;

import com.example.back_end.model.TravelPlan;
import com.example.back_end.service.TravelPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value="/api/v1/travelplan")
@CrossOrigin(origins = "http://localhost:5173")
public class TravelPlanController {

    @Autowired
    private TravelPlanService travelerPlanService;


    @GetMapping("/{travelerPlanId}/{travelerId}")
    public ResponseEntity<TravelPlanDto> getTraveler(
            @PathVariable Long travelerPlanId,
            @PathVariable String travelerId) {
        return new ResponseEntity<>(travelerPlanService.getTravelerPlanByID(travelerPlanId, travelerId), HttpStatus.OK);
    }



    @GetMapping("/accepeted/{travelerId}")
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
}
