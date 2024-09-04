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
public class TravelDestination {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long index;

    @Column(nullable = false)
    private Float lat;

    @Column(nullable = false)
    private Float longi;

    @Column(nullable = false)
    private Float rating;

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

    private String date;

    @ManyToOne
    @JoinColumn(name = "travelplan_id")
    @JsonBackReference(value = "traveldestinations")
    private TravelPlan travelPlan;

    // Cascade on REMOVE to delete the TravelBudget when TravelDestination is deleted
    @OneToOne(mappedBy = "travelDestination", cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JsonManagedReference("travelDestination-travelBudget")
    private TravelBudget travelBudget;
}
