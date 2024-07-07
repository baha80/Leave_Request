package com.example.demo.Service;

import org.springframework.stereotype.Service;


import org.springframework.stereotype.Service;

@Service
public class EmailService {
    public void sendNotification(String to, String subject, String body) {
        // Implement email sending logic here

        System.out.println("Sending email to: " + to + ", Subject: " + subject + ", Body: " + body);
    }
}