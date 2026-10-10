package org.example.orderservice.service;

import lombok.AllArgsConstructor;
import org.example.orderservice.OrderMapper;
import org.example.orderservice.StatusOrder;
import org.example.orderservice.dto.OrderDto;
import org.example.orderservice.entity.OrderEntity;
import org.example.orderservice.entity.Product;
import org.example.orderservice.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {
    private static final Logger log = LoggerFactory.getLogger(OrderService.class);
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;


    public List<OrderDto> getOrderById(Long id, Pageable pageable) {
        orderRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Order not found by id " + id));

        return orderRepository.getAllOrdersById(id, pageable)
                .stream()
                .map(it -> orderMapper.toOrderDto(it)).toList();
    }

    public OrderDto createOrders(OrderDto orderDto) {
        var orderEntity = new OrderEntity(
                null,
                null,
                orderDto.products()
                        .stream()
                        .map(it -> orderMapper.toProductEntity(it)).toList(),
                null,
                StatusOrder.PENDING
        );
        orderRepository.save(orderEntity);
        return orderMapper.toOrderDto(orderEntity);
    }

    public Boolean updateStatusOrder(Long id) {

        OrderEntity orderEntity = orderRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Order not found by id " + id));

        orderEntity.setAllPrice(calculatePrice(orderEntity));
        log.info("before setStatusOrder");
        orderEntity.setStatusOrder(StatusOrder.APPROVED);
        orderRepository.save(orderEntity);
        return true;
    }

    private BigDecimal calculatePrice(OrderEntity orderEntity) {
//        return orderEntity.getProducts()
//                .stream()
//                .map(product -> product
//                        .getPrice()
//                        .multiply(BigDecimal.valueOf(product.getCount())))
//                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal result = BigDecimal.ZERO;
        for (Product product : orderEntity.getProducts()) {
            result = result.add(
                    product.getPrice().multiply(BigDecimal.valueOf(product.getCount())));
        }
        return result;
    }
}
