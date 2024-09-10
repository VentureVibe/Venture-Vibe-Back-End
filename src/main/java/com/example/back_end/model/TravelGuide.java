package com.example.back_end.model;

import com.example.back_end.dto.ExperienceDTO;
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
}