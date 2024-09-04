package com.example.back_end.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExperienceDTO {
    private Long id;
    private String companyName;
    private String role;
    private Integer yearsOfExperience;
}