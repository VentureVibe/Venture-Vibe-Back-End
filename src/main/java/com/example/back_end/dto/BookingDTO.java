package com.example.back_end.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import software.amazon.awssdk.services.s3.endpoints.internal.Value;

import java.time.LocalDate;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookingDTO {
    private Long id;
    private String travelGuideId;
    private String bookerId;
    private Double amount;
    private Integer noOfDays;
    private List<LocalDate> bookedDates;
}
