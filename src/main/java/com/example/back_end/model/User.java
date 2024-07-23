package com.example.back_end.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Data
@Table(name="venture_users")
public class User {

    @Id
    @Column(length = 255)
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private String userId;
    @Column(length = 255)
    private String email;
    @Column(length = 255)
    private String role;
    @Column(length = 2048)
    private String profileImageUrl;
}
