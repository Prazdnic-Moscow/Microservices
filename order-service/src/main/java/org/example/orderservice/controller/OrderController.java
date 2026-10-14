package org.example.orderservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.library.order.OrderDto;
import org.example.orderservice.service.OrderService;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping()
    public OrderDto createOrders (@RequestBody OrderDto orderDto){
        return orderService.createOrders(orderDto);
    }

    @GetMapping("/{id}")
    public List<OrderDto> getOrdersById (@PathVariable("id") Long id,
                                   Pageable pageable){
        return orderService.getOrderById(id, pageable);

    }

    @PostMapping("/{id}/payment")
    public Boolean updateStatusOrder(@PathVariable("id") Long id){
        return orderService.updateStatusOrder(id);
    }

    @PostMapping("/{id}/pay")
    public OrderDto createPayment(@PathVariable("id") Long id,
                                  @RequestBody org.example.library.payment.PaymentDto paymentDto){
        log.info("Метод установки оплаты микросервис!!!!!!!!!!!!!!!!!!!!!!!!!");
        return orderService.createPayment(id, paymentDto);
    }

}
