package com.example.back_end.controller;

import com.example.back_end.dto.SubscriberDTO;
import com.example.back_end.service.SubscriberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/subscribers")
public class SubscriberController {

    @Autowired
    private SubscriberService subscriberService;

    @PostMapping
    public ResponseEntity<String> subscribe(@RequestBody SubscriberDTO subscriberDTO){
        subscriberService.saveSubscriber(subscriberDTO.getEmail());
        return ResponseEntity.ok("Subscribed successfully");
    }

}
