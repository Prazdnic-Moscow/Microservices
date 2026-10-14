package org.example.orderservice.external;


import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;
import org.example.library.payment.PaymentDto;

@HttpExchange(
        accept = "application/json",
        contentType = "application/json",
        url = "/api/payment")

public interface PaymentHttpClient {

    @PostExchange
    PaymentDto createPayment(@RequestBody PaymentDto paymentDto);
}
