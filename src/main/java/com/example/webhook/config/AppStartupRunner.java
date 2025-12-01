package com.example.webhook.config;

import com.example.webhook.dto.GenerateWebhookRequest;
import com.example.webhook.dto.GenerateWebhookResponse;
import com.example.webhook.service.SqlSolverService;
import com.example.webhook.service.WebhookService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AppStartupRunner implements CommandLineRunner {

    private final WebhookService webhookService;
    private final SqlSolverService sqlSolverService;

    public AppStartupRunner(WebhookService webhookService, SqlSolverService sqlSolverService) {
        this.webhookService = webhookService;
        this.sqlSolverService = sqlSolverService;
    }

    @Override
    public void run(String... args) throws Exception {

        GenerateWebhookRequest req = new GenerateWebhookRequest(
                "Deepak N",
                "22BCE8822",
                "nagarajank14111974@gmail.com"
        );

        // Step 1: Call API to generate webhook
        GenerateWebhookResponse response = webhookService.generateWebhook(req);

        // Step 2: Solve SQL question
        String sqlQuery = sqlSolverService.getQueryForRegNo(req.getRegNo());

        // Step 3: Send final SQL query to webhook URL
        webhookService.sendFinalQuery(response.getWebhookUrl(), response.getAccessToken(), sqlQuery);

        System.out.println("Flow completed successfully.");
    }
}
