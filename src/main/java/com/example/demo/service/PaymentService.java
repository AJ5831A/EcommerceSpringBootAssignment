package com.example.demo.service;

import com.example.demo.model.Order;
import com.example.demo.model.Payment;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.PaymentRepository;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepo;
    private final OrderRepository orderRepo;

    public PaymentService(PaymentRepository p, OrderRepository o) {
        this.paymentRepo = p;
        this.orderRepo = o;
    }

    public Payment create(String orderId) {
        Order order = orderRepo.findById(orderId).orElseThrow();

        Payment p = new Payment();
        p.setOrderId(orderId);
        p.setAmount(order.getTotalAmount());
        p.setStatus("PENDING");

        return paymentRepo.save(p);
    }

    public void updateStatus(String orderId, String status) {
        Payment p = paymentRepo.findByOrderId(orderId);
        Order o = orderRepo.findById(orderId).orElseThrow();

        p.setStatus(status);
        o.setStatus(status.equals("SUCCESS") ? "PAID" : "FAILED");

        paymentRepo.save(p);
        orderRepo.save(o);
    }


}
