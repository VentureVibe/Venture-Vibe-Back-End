package com.example.back_end.dto;

import com.example.back_end.model.TravelPlan;
import com.example.back_end.model.Traveler;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TravelerDto {

    private String id;

    private String email;

    private String name;

    private String country;

    private String firstName;

    private String lastName;

    private String password;

    private String role;

    private String profileImg;

    private String coverImg;

    private List<TravelPlan> travelplans;


}
