package com.example.back_end.controller;

import com.example.back_end.dto.TravelerDto;
import com.example.back_end.service.TravelerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value="/api/v1/public/traveler")
@CrossOrigin(origins = "http://localhost:5173")
public class TravelerController {

    @Autowired
    private TravelerService travelerService;


    @GetMapping("/{travelerId}")
    public ResponseEntity<TravelerDto> getTraveler(@PathVariable String travelerId){
        return new ResponseEntity<>(travelerService.getTravelerByID(travelerId), HttpStatus.CREATED);
    }

    @GetMapping("/emailpartially/{email}")
    public ResponseEntity<List<TravelerDto>> getTravelerByEmailPartially(@PathVariable String email){
        return new ResponseEntity<>(travelerService.getTravelerByEmailPartially(email), HttpStatus.CREATED);
    }


    @PostMapping()
    public ResponseEntity<TravelerDto> addTraveler(@RequestBody TravelerDto traveler) {

        TravelerDto addedProduct = travelerService.addTraveler(traveler);

        return new ResponseEntity<>(addedProduct, HttpStatus.CREATED);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<TravelerDto> deleteTraveler(@PathVariable String id) {
        TravelerDto deleteTraveler = travelerService.deleteTraveler(id);
        return new ResponseEntity<>(deleteTraveler, HttpStatus.CREATED);

    }
}
