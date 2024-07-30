package com.example.back_end.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer eventId;
    private String userId;
    private String eventTitle;
    private String eventDescription;
    private Integer eventPrice;
    private Float eventLat;
    private Float eventLon;
    private String contactEmail;
    private String contactPhone;
    private String eventImage;
}
