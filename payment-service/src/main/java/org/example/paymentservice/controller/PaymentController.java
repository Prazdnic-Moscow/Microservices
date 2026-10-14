package org.example.paymentservice.controller;

import lombok.RequiredArgsConstructor;
import org.example.library.payment.PaymentDto;
import org.example.paymentservice.service.PaymentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping()
    public PaymentDto setStatusPayment(@RequestBody PaymentDto paymentDto){
        return paymentService.setStatusPayment(paymentDto);

    }
}
