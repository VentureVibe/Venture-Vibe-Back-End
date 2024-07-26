package com.example.back_end.dto;

import com.example.back_end.model.TravelDate;
import com.example.back_end.model.Traveler;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TravelPlanDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private Float lat;

    private Float longi;


    private String location;


    private String fromDate;


    private String toDate;

    private List<Traveler> travelers;
    private List<TravelDate> travelDates;
    private Traveler travelPlanOwner;
}
