/*package com.example.back_end.dto;

import com.example.back_end.model.Experience;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ServiceProviderDTO {
    private String id;
    private String role;
    private LocalDateTime purchaseDate;
    private LocalDateTime expirationDate;
    private String contactNumber;
    private String email;
    private Float sp_lat;
    private Float sp_lng;
    private String planType;
    private Float radius;
    private List<ExperienceDTO> experiences;
    private List<TravelGuideServiceDTO> travelGuideServices;
}*/

// src/main/java/com/example/back_end/dto/ServiceProviderDTO.java
package com.example.back_end.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ServiceProviderDTO {
    private String id;
    private String role;
    private LocalDateTime purchaseDate;
    private LocalDateTime expirationDate;
    private String contactNumber;
    private String email;
    private Float sp_lat;
    private Float sp_lng;
    private String planType;
    private Float radius;
    private Double price;
    private List<String> specialties;
    private List<String> languages;
    private List<ExperienceDTO> experiences;
    private List<TravelGuideServiceDTO> travelGuideServices;
}