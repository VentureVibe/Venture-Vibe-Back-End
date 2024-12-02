package com.example.back_end.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TravelGuideReviewDTO {
    private Long id;
    private String fromUser;
    private String toGuide;
    private LocalDate date;
    private int rating;
    private String comment;
    private String tourDuration;
}
