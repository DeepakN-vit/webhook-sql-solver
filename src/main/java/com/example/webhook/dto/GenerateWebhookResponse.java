package com.example.webhook.dto;

public class GenerateWebhookResponse {

    private String webhook;
    private String accessToken;

    public String getWebhookUrl() { return webhook; }
    public String getAccessToken() { return accessToken; }

    public void setWebhook(String webhook) { this.webhook = webhook; }
    public void setAccessToken(String accessToken) { this.accessToken = accessToken; }
}
