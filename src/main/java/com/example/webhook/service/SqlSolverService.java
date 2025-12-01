package com.example.webhook.service;

import org.springframework.stereotype.Service;

@Service
public class SqlSolverService {

    public String getQueryForRegNo(String regNo) {

        int lastDigit = Character.getNumericValue(regNo.charAt(regNo.length() - 1));

        // Even → Question 2, Odd → Question 1
        if (lastDigit % 2 == 0) {
            return getQuestion2Query();
        } else {
            return getQuestion1Query();
        }
    }

    // Placeholder for Question 1 (you can modify if needed)
    private String getQuestion1Query() {
        return "SELECT 'QUESTION 1 SQL NOT REQUIRED FOR THIS ASSIGNMENT' AS INFO;";
    }

    // ⭐ Correct SQL solution for QUESTION 2 (from PDF)
    private String getQuestion2Query() {
        return "SELECT "
                + "d.DEPARTMENT_NAME, "
                + "AVG(TIMESTAMPDIFF(YEAR, e.DOB, CURRENT_DATE)) AS AVERAGE_AGE, "
                + "("
                + "   SELECT GROUP_CONCAT(full_name SEPARATOR ', ') "
                + "   FROM ( "
                + "       SELECT CONCAT(e2.FIRST_NAME, ' ', e2.LAST_NAME) AS full_name "
                + "       FROM EMPLOYEE e2 "
                + "       JOIN PAYMENTS p2 ON e2.EMP_ID = p2.EMP_ID "
                + "       WHERE e2.DEPARTMENT = d.DEPARTMENT_ID "
                + "         AND p2.AMOUNT > 70000 "
                + "       ORDER BY p2.PAYMENT_TIME "
                + "       LIMIT 10 "
                + "   ) AS names_list "
                + ") AS EMPLOYEE_LIST "
                + "FROM DEPARTMENT d "
                + "JOIN EMPLOYEE e ON d.DEPARTMENT_ID = e.DEPARTMENT "
                + "JOIN PAYMENTS p ON e.EMP_ID = p.EMP_ID "
                + "WHERE p.AMOUNT > 70000 "
                + "GROUP BY d.DEPARTMENT_ID, d.DEPARTMENT_NAME "
                + "ORDER BY d.DEPARTMENT_ID DESC;";
    }
}
