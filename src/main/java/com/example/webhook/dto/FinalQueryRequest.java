package com.example.webhook.dto;

public class FinalQueryRequest {
    private String finalQuery;

    public FinalQueryRequest(String finalQuery) {
        this.finalQuery = finalQuery;
    }

    public String getFinalQuery() { return finalQuery; }
}
