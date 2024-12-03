package com.example.back_end.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaymentDTO {
    private String sender;
    private String receiver;
    private String dateTime;
    private Double amount;
    private String category;
}
