package com.example.back_end.controller;

import com.example.back_end.dto.ReviewDTO;
import com.example.back_end.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/review")
@CrossOrigin("http://localhost:5173")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @PostMapping("/save")
    public ResponseEntity<ReviewDTO> saveReview(@RequestBody ReviewDTO reviewDTO) {
        ReviewDTO savedReview = reviewService.saveReview(reviewDTO);
        return ResponseEntity.ok(savedReview);
    }

    @GetMapping("/receiver/{receiver}")
    public ResponseEntity<List<ReviewDTO>> getReviewsByReceiver(@PathVariable String receiver) {
        List<ReviewDTO> reviews = reviewService.getReviewsByReceiver(receiver);
        return ResponseEntity.ok(reviews);
    }
}