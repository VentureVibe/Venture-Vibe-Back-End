package com.example.back_end.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Conversation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user1_id", nullable = false)
    private Traveler user1;

    @ManyToOne
    @JoinColumn(name = "user2_id", nullable = false)
    private Traveler user2;

    private LocalDateTime startTime;
    private Boolean readByUser1;
    private Boolean readByUser2;

    @Column(length = 2048)
    private String lastMessage;

    @JsonIgnore
    @OneToMany(mappedBy = "conversation", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Message> messages = new HashSet<>();

    // Getters and setters
}

