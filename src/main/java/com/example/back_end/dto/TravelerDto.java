package com.example.back_end.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TravelerDto {

    private String id;

    private String email;

    private String name;

    private String password;

    private String profileImg;

    private String coverImg;
}
