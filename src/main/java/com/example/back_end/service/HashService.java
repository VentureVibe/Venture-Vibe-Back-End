package com.example.back_end.service;

import com.example.back_end.dto.HashRequestDTO;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

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

    private String CLIENT_CREDENTIALS;

    @Value("${payhere.authorizationCode}")
    private String authorizationCode;

    // Initialize CLIENT_CREDENTIALS in the constructor after Spring has injected the value
    @PostConstruct
    public void init() {
        CLIENT_CREDENTIALS = "Basic " + authorizationCode;
    }


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

    private final String PAYHERE_TOKEN_URL = "https://sandbox.payhere.lk/merchant/v1/oauth/token";

    // Method to retrieve access token
    public String getAccessToken() {
        System.out.println("get access token ekata awa");
        System.out.println("client cre : "+CLIENT_CREDENTIALS);
        System.out.println("client cre : "+authorizationCode);
        RestTemplate restTemplate = new RestTemplate();

        // Set up headers
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", CLIENT_CREDENTIALS);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        LinkedMultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("grant_type", "client_credentials");

        // Create HttpEntity for the request
        HttpEntity<LinkedMultiValueMap<String, String>> requestEntity = new HttpEntity<>(requestBody, headers);

         ResponseEntity<Map> response = null;

    try {
        // Send request to PayHere OAuth token endpoint
        response = restTemplate.exchange(PAYHERE_TOKEN_URL, HttpMethod.POST, requestEntity, Map.class);

        // Log response status code and body
        System.out.println("Response status code: " + response.getStatusCode());
        System.out.println("Response body: " + response.getBody());

        // Get access token from the response
        Map<String, String> responseBody = response.getBody();
        return responseBody != null ? responseBody.get("access_token") : null;

    } catch (HttpClientErrorException e) {
        // Log HTTP client error (4xx status codes)
        System.out.println("Client Error (4xx) - Status Code: " + e.getStatusCode());
        System.out.println("Response Body: " + e.getResponseBodyAsString());

    } catch (HttpServerErrorException e) {
        // Log HTTP server error (5xx status codes)
        System.out.println("Server Error (5xx) - Status Code: " + e.getStatusCode());
        System.out.println("Response Body: " + e.getResponseBodyAsString());

    } catch (Exception e) {
        // Log any other exceptions that may occur
        System.out.println("An unexpected error occurred: " + e.getMessage());
        e.printStackTrace();
    }

    return null;
    }

    private final String PAYHERE_REFUND_URL = "https://sandbox.payhere.lk/merchant/v1/payment/refund";

    public Map<String, Object> refundPayment(String paymentId, String description) {
    System.out.println("service eke refund payment ekata awa");
    // Get access token first
    String accessToken = getAccessToken();
    System.out.println("token eka gaththa :" + accessToken);
    if (accessToken == null) {
        throw new RuntimeException("Unable to retrieve access token");
    }

    // Set up headers
    HttpHeaders headers = new HttpHeaders();
    headers.set("Authorization", "Bearer " + accessToken);
    System.out.println("Bearer " + accessToken);
    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

    // Create refund request body
    LinkedMultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
    requestBody.add("payment_id", paymentId);
    requestBody.add("description", description);

    // Log the request body for debugging
    System.out.println("Request Body: " + requestBody.toString());

    // Create HttpEntity for the request
    HttpEntity<LinkedMultiValueMap<String, String>> requestEntity = new HttpEntity<>(requestBody, headers);

    // Send refund request to PayHere
    RestTemplate restTemplate = new RestTemplate();
    ResponseEntity<Map> response = null;

    try {
        response = restTemplate.exchange(PAYHERE_REFUND_URL, HttpMethod.POST, requestEntity, Map.class);

        // Process the response
        System.out.println("Response status code: " + response.getStatusCode());
        System.out.println("Response body: " + response.getBody());
        return response.getBody();
    } catch (HttpClientErrorException e) {
        System.out.println("Client Error (4xx) - Status Code: " + e.getStatusCode());
        System.out.println("Response Body: " + e.getResponseBodyAsString());
    } catch (HttpServerErrorException e) {
        System.out.println("Server Error (5xx) - Status Code: " + e.getStatusCode());
        System.out.println("Response Body: " + e.getResponseBodyAsString());
    } catch (Exception e) {
        System.out.println("An unexpected error occurred: " + e.getMessage());
        e.printStackTrace();
    }

    return null;
}


}
