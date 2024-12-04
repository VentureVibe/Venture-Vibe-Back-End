package com.example.back_end.controller;

import com.example.back_end.dto.UnavailableDatesDTO;
import com.example.back_end.service.UnavailableDatesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/unavailable-dates")
@CrossOrigin("http://localhost:5173")
public class UnavailableDatesController {
    @Autowired
    private UnavailableDatesService unavailableDatesService;

    @PostMapping("/save")
    public ResponseEntity<UnavailableDatesDTO> saveUnavailableDates(@RequestBody UnavailableDatesDTO unavailableDatesDTO) {
        UnavailableDatesDTO savedUnavailableDates = unavailableDatesService.saveUnavailableDates(unavailableDatesDTO);
        return ResponseEntity.ok(savedUnavailableDates);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<UnavailableDatesDTO> getUnavailableDatesByUserId(@PathVariable String userId) {
        UnavailableDatesDTO unavailableDates = unavailableDatesService.getUnavailableDatesByUserId(userId);
        if (unavailableDates == null) {
            return ResponseEntity.ok(null);
        }
        return ResponseEntity.ok(unavailableDates);
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<UnavailableDatesDTO> updateUnavailableDates(@PathVariable String userId, @RequestBody List<LocalDate> newUnavailableDates) {
        UnavailableDatesDTO updatedUnavailableDates = unavailableDatesService.updateUnavailableDates(userId, newUnavailableDates);
        if (updatedUnavailableDates == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedUnavailableDates);
    }
}