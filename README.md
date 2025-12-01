Webhook SQL Solver – Spring Boot Application

This project is a Spring Boot application built to automate a hiring assignment workflow. The application runs without any REST controllers and performs all required operations as soon as it starts. It interacts with the provided external APIs, processes the assigned SQL problem, and submits the final SQL query automatically to the generated webhook URL.

Project Overview

When the application starts, it performs the following steps:

Sends a POST request to the external API endpoint to generate a webhook URL and an access token.

Determines the SQL question to solve based on the last digit of the registration number.

Generates the correct SQL query for the assigned problem.

Submits the final SQL answer to the webhook URL using the provided JWT token in the Authorization header.

The entire workflow is executed on application startup using a CommandLineRunner.

Technologies Used

Java 17

Spring Boot 3

Maven

RestTemplate for HTTP communication

How the Application Works
1. Generating the Webhook

The application sends the required details such as name, registration number and email to the external API. The response contains:

A webhook URL

An access token used for authentication

This data is then used in the submission step.

2. Solving the SQL Question

Based on the registration number, the application automatically selects either Question 1 or Question 2. The SQL problem is solved inside the SqlSolverService class. For Question 2, the query calculates the average age of employees earning more than seventy thousand and returns a comma-separated list containing up to ten employee names per department.

3. Sending the Final SQL Query

The final SQL string is wrapped in a JSON object and sent to the webhook URL. The access token is included in the Authorization header using the Bearer token format.

There is no controller in this project. The entire flow is executed automatically on startup.

How to Build and Run the Application
Step 1: Build the JAR

Run the following command:

mvn clean package


This generates the JAR file inside the target directory.

Step 2: Run the Application

Use the command below:

java -jar target/webhook-sql-solver-1.0.0.jar


After running, the console will display a message indicating that the flow has completed. At this point, your SQL solution has been successfully submitted to the generated webhook.

Project Structure
src/main/java/com/example/webhook
│
├── WebhookSqlSolverApplication.java
├── config
│   └── AppStartupRunner.java
├── dto
│   ├── GenerateWebhookRequest.java
│   ├── GenerateWebhookResponse.java
│   └── FinalQueryRequest.java
├── service
│   ├── WebhookService.java
│   └── SqlSolverService.java
└── util
    └── SimpleJwtUtil.java