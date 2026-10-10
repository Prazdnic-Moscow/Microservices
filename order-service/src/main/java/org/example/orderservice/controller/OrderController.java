package org.example.orderservice.controller;

import lombok.RequiredArgsConstructor;
import org.example.orderservice.dto.OrderDto;
import org.example.orderservice.service.OrderService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping()
    public OrderDto createOrders (){
        return orderService.createOrders();
    }

    @GetMapping()
    public OrderDto getAllOrdersByFilter(Pageable pageable){
        return orderService.getAllOrdersByFilter(pageable);
    }

    @GetMapping("/{id}")
    public OrderDto getOrdersById (Long id,
                                   @PathVariable("id") String id){
        return orderService.getOrdersById(id);

    }

    @PostMapping("/{id}/pay")
    public Boolean updateStatusPayment(@PathVariable("id") String id){
        return orderService.updateStatusPayment(id);
    }

}
