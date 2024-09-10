package com.example.back_end.controller;

import com.example.back_end.dto.HashRequestDTO;
import com.example.back_end.service.HashService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/payment")
@CrossOrigin("http://localhost:5173")
public class PaymentController {

    private final HashService hashService;

    public PaymentController(HashService hashService) {
        this.hashService = hashService;
    }

    @PostMapping("/generateHash")
    public ResponseEntity<Map> generateHash(@RequestBody HashRequestDTO request) throws NoSuchAlgorithmException {
        Map<String, String> response = hashService.generateHash(request);
        return ResponseEntity.ok(response);
    }

}
