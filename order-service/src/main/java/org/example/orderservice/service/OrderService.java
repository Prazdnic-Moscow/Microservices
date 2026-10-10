package org.example.orderservice.service;

import org.example.orderservice.dto.OrderDto;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class OrderService {


    public OrderDto getOrdersById(Long id) {
        var order = new OrderDto(1L);
        return order;
    }

    public OrderDto getAllOrdersByFilter(Pageable pageable) {
        var order = new OrderDto(1L);
        return order;
    }

    public OrderDto createOrders() {
        return null;
    }

    public Boolean updateStatusPayment(String id) {
        return true;
    }
}
