package com.example.back_end.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
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
public class TravelDestination {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long index;

    @Column(nullable = false)
    private Float lat;

    @Column(nullable = false)
    private Float longi;

    @Column(length = 2048)
    private String imgUrl;

    @Column(length = 5000)
    private String description;

    @Column(length = 5000)
    private String name;

    @Column(length = 5000)
    private String type;

    @ManyToOne
    @JoinColumn(name = "traveler_id")
    private Traveler traveler;

    private String Date;

    @ManyToOne
    @JoinColumn(name = "travelplan_id")
    @JsonBackReference(value = "traveldestinations")
    private TravelPlan travelPlan;

    @OneToOne
    @JoinColumn(name="budget_id")
    private TravelBudget travelBudget;



}
