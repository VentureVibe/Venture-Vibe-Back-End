package com.example.back_end.controller;

import com.example.back_end.dto.TravelGuideServiceDTO;
import com.example.back_end.dto.TravelerDto;
import com.example.back_end.exception.deletefailed.DeleteFailed;
import com.example.back_end.model.TravelGuideServices;
import com.example.back_end.model.Traveler;
import com.example.back_end.service.TravelGuideServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/api/v1/travelguideservice")
@CrossOrigin("http://localhost:5173")
public class TravelGuideServiceController {

    @Autowired
    TravelGuideServiceService  travelGuideServiceService;



    @PostMapping("")
    public ResponseEntity<TravelGuideServiceDTO> addTravelGuideService(@RequestBody TravelGuideServiceDTO travelGuideServiceDTO) {

        TravelGuideServiceDTO addedProduct = travelGuideServiceService.addTravelGuideService(travelGuideServiceDTO);
        return new ResponseEntity<>(addedProduct, HttpStatus.CREATED);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<TravelGuideServiceDTO> deleteTravelGuideService(@PathVariable Long id) {
        TravelGuideServiceDTO deleteTraveler = travelGuideServiceService.deleteTravelGuideService(id);
        return new ResponseEntity<>(deleteTraveler, HttpStatus.CREATED);
    }

    @GetMapping("/travel-guides")
    public List<TravelGuideServiceDTO> getAllTravelGuideServices() {
        return travelGuideServiceService.getAllTravelGuideServices();
    }


}
