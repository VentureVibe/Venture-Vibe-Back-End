package com.example.back_end.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

    @Column(length = 5000)
    private String note;

    @Column(nullable = false)
    private String fromDate;

    @Column(nullable = false)
    private String toDate;

    @Column(length = 2048)
    private String imgUrl;

    @ManyToMany(mappedBy = "travelplans")
    @JsonBackReference
    private List<Traveler> travelers = new ArrayList<>();

    @OneToMany(mappedBy = "travelPlan", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TravelDate> travelDates = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name ="travelplanOwner")
    @JsonBackReference(value = "owner-travelplan")

    private Traveler travelPlanOwner;
}
