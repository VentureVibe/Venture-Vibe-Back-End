package com.example.back_end.service;

import com.example.back_end.dto.HashRequestDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class HashService {
    @Value("${payhere.merchantId}")
    private String merchantId;

    @Value("${payhere.merchantSecret}")
    private String merchantSecret;

    public Map<String, String> generateHash(HashRequestDTO request) throws NoSuchAlgorithmException {
        String orderId = UUID.randomUUID().toString();
        String amount = String.format("%.2f", request.getAmount());
        String currency = request.getCurrency();

        String hashString = merchantId + orderId + amount + currency + md5(merchantSecret).toUpperCase();
        String hash = md5(hashString).toUpperCase();
        Map<String, String> response = new HashMap<>();
        response.put("hash", hash);
        response.put("orderId", orderId);
        return response;
    }

    private String md5(String input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] messageDigest = md.digest(input.getBytes());
        StringBuilder sb = new StringBuilder();
        for (byte b : messageDigest) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
