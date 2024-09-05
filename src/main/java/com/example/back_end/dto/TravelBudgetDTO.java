package com.example.back_end.dto;

import com.example.back_end.model.TravelDestination;
import com.example.back_end.model.TravelPlan;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TravelBudgetDTO {

    private Long id;
    private Float cost;


    private String type;


    private String date;


    private String description;


    private TravelDestination travelDestination;

    private TravelerTravelPlanDTO traveler;
}
