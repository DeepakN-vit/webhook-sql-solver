package com.example.webhook.util;

import org.springframework.http.HttpHeaders;

public class SimpleJwtUtil {

    public static HttpHeaders createJwtHeaders(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        headers.set("Content-Type", "application/json");
        return headers;
    }
}
