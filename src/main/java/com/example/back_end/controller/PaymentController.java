package com.example.back_end.controller;

import com.example.back_end.dto.HashRequestDTO;
import com.example.back_end.service.HashService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/payment")
@CrossOrigin("http://localhost:5173")
public class PaymentController {

    @Autowired
    private final HashService hashService;

    public PaymentController(HashService hashService) {
        this.hashService = hashService;
    }

    @PostMapping("/generateHash")
    public ResponseEntity<Map> generateHash(@RequestBody HashRequestDTO request) throws NoSuchAlgorithmException {
        Map<String, String> response = hashService.generateHash(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/notify")
    public ResponseEntity<String> handlePaymentNotification(@RequestParam Map<String, String> notificationData) {
        String paymentId = notificationData.get("payment_id");
        String status = notificationData.get("status_code");

        System.out.println("payment id : " + paymentId);
        System.out.println("status code : " + status);

        // Process the payment notification data here
        if ("2".equals(status)) { // '2' indicates successful payment
            // Handle successful payment logic (e.g., save paymentId, orderId to the database)
        }

        return ResponseEntity.ok("Notification received");
    }

    @PostMapping("/refund")
    public ResponseEntity<Map<String, Object>> refundPayment(@RequestParam String paymentId, @RequestParam String description) {
        System.out.println("backend ekata awa");
        Map<String, Object> response = hashService.refundPayment(paymentId, description);
        System.out.println("refund payment id : " + paymentId);
        return ResponseEntity.ok(response);
    }

}
