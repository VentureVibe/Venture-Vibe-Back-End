package com.example.back_end.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TravelBudget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Float cost;

    private String type;

    @Column(nullable = true)
    private String date;

    @Column(length = 5000)
    private String description;

    @ManyToOne
    @JoinColumn(name = "travelplan_id")
    @JsonBackReference(value = "travelplan-budget")
    private TravelPlan travelPlan;

    // No cascade here, as TravelBudget should not affect TravelDestination

    @OneToOne
    @JoinColumn(name = "destination_id", unique = true)
    @JsonBackReference("travelDestination-travelBudget")
    private TravelDestination travelDestination;

    @ManyToOne(optional = true)
    @JoinColumn(name = "traveler_id")
    @JsonBackReference(value = "a")
    private Traveler traveler;
}
