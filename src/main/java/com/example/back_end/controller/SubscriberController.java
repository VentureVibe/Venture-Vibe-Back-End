package com.example.back_end.controller;

import com.example.back_end.dto.SubscriberDTO;
import com.example.back_end.model.EmailSubscriber;
import com.example.back_end.repository.SubscriberRepository;
import com.example.back_end.service.SubscriberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subscribers")
@CrossOrigin("http://localhost:5173")
public class SubscriberController {

    @Autowired
    private SubscriberService subscriberService;

    @Autowired
    private SubscriberRepository subscriberRepository;

    @PostMapping
    public ResponseEntity<String> subscribe(@RequestBody SubscriberDTO subscriberDTO){
        subscriberService.saveSubscriber(subscriberDTO.getEmail());
        return ResponseEntity.ok("Subscribed successfully");
    }
    @GetMapping
    public List<EmailSubscriber> getAllSubscribers() {
        return subscriberRepository.findAll();
    }

}
