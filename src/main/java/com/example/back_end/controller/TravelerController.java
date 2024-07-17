package com.example.back_end.controller;

import com.example.back_end.dto.TravelerDto;
import com.example.back_end.service.TravelerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping(value="/api/v1/public/traveler")
public class TravelerController {
    @Autowired
    private TravelerService travelerService;

    @PostMapping
    public ResponseEntity<TravelerDto> addTraveler(@RequestBody TravelerDto traveler) {

        TravelerDto addedProduct = travelerService.addTraveler(traveler);

        return ResponseEntity.created(URI.create("")).body(addedProduct);
    }
}
