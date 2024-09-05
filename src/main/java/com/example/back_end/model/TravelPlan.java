package com.example.back_end.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TravelPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Float lat;

    @Column(nullable = false)
    private Float longi;

    @Column(length = 2048, nullable = false)
    private String location;


    private Long budget = 0L;


    @Column(length = 5000)
    private String note;

    @Column(nullable = false)
    private String fromDate;

    @Column(nullable = false)
    private String toDate;

    @Column(length = 2048)
    private String imgUrl;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @ManyToMany(mappedBy = "travelplans")
    @JsonBackReference
    private List<Traveler> travelers = new ArrayList<>();

    @OneToMany(mappedBy = "travelPlan", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TravelDate> travelDates = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name ="travelplanOwner")
    private Traveler travelPlanOwner;

    @OneToMany(mappedBy = "travelPlan", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "travelplan-invitations")
    private List<TravelInvitation> travelInvitations = new ArrayList<>();

    @OneToMany(mappedBy = "travelPlan", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "travelplan-budget")
    private List<TravelBudget> travelBudgets = new ArrayList<>();

    @OneToMany(mappedBy = "travelPlan", cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JsonManagedReference(value = "traveldestinations")
    private List<TravelDestination> travelDestinations = new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        if (createdAt == null) {
            createdAt = new Date();
        }
    }

}