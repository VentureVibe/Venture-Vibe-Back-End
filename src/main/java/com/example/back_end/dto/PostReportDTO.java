package com.example.back_end.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostReportDTO {


        private Long reportId;
        private String status; // "KEEP" or "REJECT"
        private String reason;

}
