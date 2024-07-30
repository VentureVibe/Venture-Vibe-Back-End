package com.example.back_end.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EventDTO {
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
