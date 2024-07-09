package com.example.back_end.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Traveler {

    @Id
    private Integer id;

    @Column(length = 320)
    private String email;

    @Column(length = 255)
    private String name;

    @Column(length = 255)
    private String password;

    @Column(length = 2048)
    private String profileImg;

    @Column(length = 2048)
    private String coverImg;

}
