package com.example.quiz_service.Listners;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

    @JmsListener(destination = "sample-queue")
    public void listen(String message) {
        System.out.println("Received message: " + message);
    }
}
