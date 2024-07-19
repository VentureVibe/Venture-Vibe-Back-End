package com.example.back_end.controller;

import com.example.back_end.dto.TravelPlanDto;
import com.example.back_end.dto.TravelerDto;

import com.example.back_end.model.TravelPlan;
import com.example.back_end.service.TravelPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping(value="/api/v1/public/travelplan")
public class TravelPlanController {

    @Autowired
    private TravelPlanService travelerPlanService;


    @GetMapping("/{travelerPlanId}")
    public ResponseEntity<TravelPlanDto> getTraveler(@PathVariable Long travelerPlanId){
        return new ResponseEntity<>(travelerPlanService.getTravelerPlanByID(travelerPlanId), HttpStatus.CREATED);
    }


    @PostMapping("/{travelerId}")
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
