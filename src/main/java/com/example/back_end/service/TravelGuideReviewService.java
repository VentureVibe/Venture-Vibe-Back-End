package com.example.back_end.service;

import com.example.back_end.dto.TravelGuideReviewDTO;
import com.example.back_end.model.TravelGuide;
import com.example.back_end.model.TravelGuideReview;
import com.example.back_end.repository.TravelGuideRepository;
import com.example.back_end.repository.TravelGuideReviewRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TravelGuideReviewService {
    @Autowired
    private TravelGuideReviewRepository travelGuideReviewRepository;

    @Autowired
    private TravelGuideRepository travelGuideRepository;

    @Autowired
    private ModelMapper modelMapper;

    public TravelGuideReviewDTO addReview(TravelGuideReviewDTO reviewDTO) {
        TravelGuideReview review = modelMapper.map(reviewDTO, TravelGuideReview.class);
        TravelGuide travelGuide = travelGuideRepository.findById(reviewDTO.getToGuide())
                .orElseThrow(() -> new RuntimeException("TravelGuide not found with id: " + reviewDTO.getToGuide()));
        review.setTravelGuide(travelGuide);
        review = travelGuideReviewRepository.save(review);
        return modelMapper.map(review, TravelGuideReviewDTO.class);
    }

    public List<TravelGuideReviewDTO> getReviewsByTravelGuideId(String travelGuideId) {
        return travelGuideReviewRepository.findByTravelGuideId(travelGuideId).stream()
                .map(review -> modelMapper.map(review, TravelGuideReviewDTO.class))
                .collect(Collectors.toList());
    }
}
