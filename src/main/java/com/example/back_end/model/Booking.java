package com.example.back_end.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String travelGuideId;
    private String bookerId;
    private Double amount;
    private Integer noOfDays;

    @ElementCollection
    private List<LocalDate> bookedDates;
}

