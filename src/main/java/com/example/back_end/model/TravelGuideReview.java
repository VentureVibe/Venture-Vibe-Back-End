package com.example.back_end.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class TravelGuideReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fromUser;
    private String toGuide;
    private LocalDate date;
    private int rating;
    private String comment;
    private String tourDuration;

    @ManyToOne
    private TravelGuide travelGuide;
}
