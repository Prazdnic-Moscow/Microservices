package org.example.orderservice.controller;

import lombok.RequiredArgsConstructor;
import org.example.orderservice.dto.OrderDto;
import org.example.orderservice.service.OrderService;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("/{id}/pay")
    public Boolean updateStatusOrder(@PathVariable("id") Long id){
        return orderService.updateStatusOrder(id);
    }

}
