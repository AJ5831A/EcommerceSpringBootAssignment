package com.example.demo.webhook;

import com.example.demo.service.PaymentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/webhooks")
public class PaymentWebhookController {

    private final PaymentService service;

    public PaymentWebhookController(PaymentService s) {
        this.service = s;
    }

    @PostMapping("/payment")
    public void webhook(@RequestBody Map<String, String> payload) {
        service.updateStatus(payload.get("orderId"), payload.get("status"));
    }
}

