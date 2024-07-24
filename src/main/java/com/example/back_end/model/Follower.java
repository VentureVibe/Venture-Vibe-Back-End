package com.example.back_end.model;

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
public class Follower {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "follower_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "traveler_id", nullable = false)
    private Traveler traveler;

    @ManyToOne
    @JoinColumn(name = "followedTraveler_id", nullable = false)
    private Traveler followedTraveler;

    @ManyToOne
    @JoinColumn(name = "followingList_id", nullable = false)
    private FollowingList followingList;
}
