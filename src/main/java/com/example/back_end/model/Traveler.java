package com.example.back_end.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Traveler {

    @Id
    private String id;

    @Column(length = 320, nullable = false)
    private String email;

    @Column(length = 255)
    private String name;

    @Column(length = 255, nullable = false)
    private String password;

    @Column(length = 2048)
    private String profileImg;

    @Column(length = 2048)
    private String coverImg;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "traveler_travelplan",
            joinColumns = @JoinColumn(name = "traveler_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "travelplan_id", referencedColumnName = "id")
    )

    private List<TravelPlan> travelplans = new ArrayList<>();

}
