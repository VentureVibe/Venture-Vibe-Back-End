package com.example.back_end.dto;

import com.example.back_end.model.TravelGuide;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TravelGuideServiceDTO {

    private Long id;

    private String service;

    private String title;

    private String img;

    private TravelGuide travelGuide;

    private Float price;

    private String status;
}
