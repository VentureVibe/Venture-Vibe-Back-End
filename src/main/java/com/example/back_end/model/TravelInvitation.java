package com.example.back_end.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TravelInvitation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Temporal(TemporalType.TIMESTAMP)
    private Date date;


    private String state = "false";

    @ManyToOne
    @JoinColumn(name = "travelplan_id")
    @JsonBackReference(value = "travelplan-invitations")
    private TravelPlan travelPlan;

    @ManyToOne
    @JoinColumn(name ="travelplanInvitaion")
    @JsonBackReference(value = "travelplan-invitee")
    private Traveler travelPlanInvitee;


    @PrePersist
    protected void onCreate() {
        if (date == null) {
            date = new Date();
        }
    }
}
