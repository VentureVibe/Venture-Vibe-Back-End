package com.example.back_end.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Data
public class ServiceProvider {
    @Id
    private String id;
    private String role;
    private LocalDateTime purchaseDate;
    private LocalDateTime expirationDate;
    private String planType;
    private String contactNumber;
    private String email;
    private Float sp_lat;
    private Float sp_lng;
}

