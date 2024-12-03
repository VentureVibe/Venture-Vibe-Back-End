package com.example.back_end.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UnavailableDatesDTO {
    private Long id;
    private String userId;
    private List<LocalDate> unavailableDates;
}
