package com.example.back_end.service;

import com.example.back_end.dto.ReviewDTO;
import com.example.back_end.model.Review;
import com.example.back_end.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    public ReviewDTO saveReview(ReviewDTO reviewDTO) {
        Review review = new Review();
        review.setSender(reviewDTO.getSender());
        review.setReceiver(reviewDTO.getReceiver());
        review.setRating(reviewDTO.getRating());
        review.setDate(reviewDTO.getDate());
        review.setComment(reviewDTO.getComment());
        review.setTourDuration(reviewDTO.getTourDuration());

        Review savedReview = reviewRepository.save(review);
        reviewDTO.setId(savedReview.getId());
        return reviewDTO;
    }

    public List<ReviewDTO> getReviewsByReceiver(String receiver) {
        List<Review> reviews = reviewRepository.findByReceiver(receiver);
        return reviews.stream().map(review -> {
            ReviewDTO dto = new ReviewDTO();
            dto.setId(review.getId());
            dto.setSender(review.getSender());
            dto.setReceiver(review.getReceiver());
            dto.setRating(review.getRating());
            dto.setDate(review.getDate());
            dto.setComment(review.getComment());
            dto.setTourDuration(review.getTourDuration());
            return dto;
        }).collect(Collectors.toList());
    }
}
