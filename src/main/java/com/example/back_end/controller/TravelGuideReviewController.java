package com.example.back_end.controller;

import com.example.back_end.dto.TravelGuideReviewDTO;
import com.example.back_end.service.TravelGuideReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reviews")
@CrossOrigin("http://localhost:5173")
public class TravelGuideReviewController {
    @Autowired
    private TravelGuideReviewService travelGuideReviewService;

    @PostMapping
    public ResponseEntity<TravelGuideReviewDTO> addReview(@RequestBody TravelGuideReviewDTO reviewDTO) {
        TravelGuideReviewDTO savedReview = travelGuideReviewService.addReview(reviewDTO);
        return ResponseEntity.ok(savedReview);
    }

    @GetMapping("/travel-guide/{id}")
    public ResponseEntity<List<TravelGuideReviewDTO>> getReviewsByTravelGuideId(@PathVariable String id) {
        List<TravelGuideReviewDTO> reviews = travelGuideReviewService.getReviewsByTravelGuideId(id);
        return ResponseEntity.ok(reviews);
    }
}
