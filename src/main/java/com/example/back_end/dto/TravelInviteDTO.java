package com.example.back_end.dto;

import com.example.back_end.model.TravelPlan;
import com.example.back_end.model.Traveler;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
public class TravelInviteDTO {

    private Long id;

    private Date date;

    private String state = "false";

    private TravelPlan travelPlan;

    private Traveler travelPlanInvitee;


}
