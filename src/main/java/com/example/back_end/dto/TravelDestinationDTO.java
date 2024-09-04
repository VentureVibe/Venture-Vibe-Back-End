package com.example.back_end.dto;

import com.example.back_end.model.TravelBudget;
import com.example.back_end.model.TravelPlan;
import com.example.back_end.model.Traveler;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Data
public class TravelDestinationDTO {

    private Long id;

    private Long index;

    private Float lat;

    private Float longi;

    private Float rating;

    private String imgUrl;

    private String description;

    private String name;

    private String type;

    private Traveler traveler;

    private String Date;

    private TravelPlan travelPlan;

    private TravelBudget travelBudget;
}
