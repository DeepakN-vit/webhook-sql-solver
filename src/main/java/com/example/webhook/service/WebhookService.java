package com.example.webhook.service;

import com.example.webhook.dto.GenerateWebhookRequest;
import com.example.webhook.dto.GenerateWebhookResponse;
import com.example.webhook.dto.FinalQueryRequest;
import com.example.webhook.util.SimpleJwtUtil;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WebhookService {

    private final RestTemplate restTemplate = new RestTemplate();

    private static final String GENERATE_URL =
            "https://bfhldevapigw.healthrx.co.in/hiring/generateWebhook/JAVA";

    public GenerateWebhookResponse generateWebhook(GenerateWebhookRequest req) {

        ResponseEntity<GenerateWebhookResponse> response = restTemplate.postForEntity(
                GENERATE_URL,
                req,
                GenerateWebhookResponse.class
        );

        return response.getBody();
    }

    public void sendFinalQuery(String webhookUrl, String token, String finalQuery) {

        FinalQueryRequest body = new FinalQueryRequest(finalQuery);

        HttpHeaders headers = SimpleJwtUtil.createJwtHeaders(token);
        HttpEntity<FinalQueryRequest> requestEntity = new HttpEntity<>(body, headers);

        restTemplate.exchange(
                webhookUrl,
                HttpMethod.POST,
                requestEntity,
                String.class
        );
    }
}
