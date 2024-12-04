package com.example.back_end.service;

import com.example.back_end.dto.UnavailableDatesDTO;
import com.example.back_end.model.UnavailableDates;
import com.example.back_end.repository.UnavailableDatesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class UnavailableDatesService {
    @Autowired
    private UnavailableDatesRepository unavailableDatesRepository;

    public UnavailableDatesDTO saveUnavailableDates(UnavailableDatesDTO unavailableDatesDTO) {
        UnavailableDates unavailableDates = new UnavailableDates();
        unavailableDates.setUserId(unavailableDatesDTO.getUserId());
        unavailableDates.setUnavailableDates(unavailableDatesDTO.getUnavailableDates());

        UnavailableDates savedUnavailableDates = unavailableDatesRepository.save(unavailableDates);
        unavailableDatesDTO.setId(savedUnavailableDates.getId());
        return unavailableDatesDTO;
    }

    public UnavailableDatesDTO getUnavailableDatesByUserId(String userId) {
        UnavailableDates unavailableDates = unavailableDatesRepository.findByUserId(userId);
        if (unavailableDates == null) {
            return null;
        }
        UnavailableDatesDTO dto = new UnavailableDatesDTO();
        dto.setId(unavailableDates.getId());
        dto.setUserId(unavailableDates.getUserId());
        dto.setUnavailableDates(unavailableDates.getUnavailableDates());
        return dto;
    }

    public UnavailableDatesDTO updateUnavailableDates(String userId, List<LocalDate> newUnavailableDates) {
        UnavailableDates unavailableDates = unavailableDatesRepository.findByUserId(userId);
        if (unavailableDates == null) {
            return null;
        }
        unavailableDates.setUnavailableDates(newUnavailableDates);
        UnavailableDates updatedUnavailableDates = unavailableDatesRepository.save(unavailableDates);
        UnavailableDatesDTO dto = new UnavailableDatesDTO();
        dto.setId(updatedUnavailableDates.getId());
        dto.setUserId(updatedUnavailableDates.getUserId());
        dto.setUnavailableDates(updatedUnavailableDates.getUnavailableDates());
        return dto;
    }
}
