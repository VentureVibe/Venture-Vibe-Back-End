package com.example.back_end.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDate;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReviewDTO {
    private Long id;
    private String sender;
    private String receiver;
    private Double rating;
    private LocalDate date;
    private String comment;
    private String tourDuration;
}
