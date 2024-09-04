package com.example.back_end.dto;

import com.example.back_end.model.TravelPlan;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TravelerTravelPlanDTO {
    private String id;

    private String email;

    private String name;

    private String password;

    private String role;

    private String profileImg;

    private String coverImg;


}
