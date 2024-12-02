/*package com.example.back_end.model;

import com.example.back_end.dto.ExperienceDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class TravelGuide extends ServiceProvider {
    private Float radius;

    @OneToMany(mappedBy = "travelGuide", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Experience> experiences;

    @OneToMany(mappedBy = "travelGuide", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<TravelGuideServices> travelGuideServices;
}*/

// src/main/java/com/example/back_end/model/TravelGuide.java
package com.example.back_end.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class TravelGuide extends ServiceProvider {
    private Float radius;
    private Double price;
    @ElementCollection
    private List<String> specialties;
    @ElementCollection
    private List<String> languages;

    @OneToMany(mappedBy = "travelGuide", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Experience> experiences;

    @OneToMany(mappedBy = "travelGuide", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TravelGuideServices> travelGuideServices;
}