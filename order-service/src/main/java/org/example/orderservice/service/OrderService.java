package org.example.orderservice.service;

import lombok.AllArgsConstructor;
import org.example.library.order.enumirate.OrderStatus;
import org.example.library.payment.enumirate.PaymentStatus;
import org.example.orderservice.OrderMapper;
import org.example.library.order.OrderDto;
import org.example.library.order.OrderEntity;
import org.example.library.order.Product;
import org.example.orderservice.external.PaymentHttpClient;
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
    private final PaymentHttpClient paymentHttpClient;


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
                OrderStatus.PENDING
        );

        orderEntity.setAllPrice(calculatePrice(orderEntity));
        orderRepository.save(orderEntity);
        return orderMapper.toOrderDto(orderEntity);
    }

    private BigDecimal calculatePrice(OrderEntity orderEntity) {

        BigDecimal result = BigDecimal.ZERO;
        for (Product product : orderEntity.getProducts()) {
            result = result.add(
                    product.getPrice().multiply(BigDecimal.valueOf(product.getCount())));
        }
        return result;
    }

    public OrderDto createPayment(Long id,
                                  org.example.library.payment.PaymentDto paymentDto) {

        OrderEntity orderEntity = orderRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Order not found by id " + id));

        if (orderEntity.getOrderStatus() == null || !orderEntity.getOrderStatus().equals(OrderStatus.PENDING)) {
            throw new RuntimeException("order must be a PENDING status");
        }
        var response = paymentHttpClient.createPayment(
                org.example.library.payment.PaymentDto.builder()
                .orderId(id)
                .paymentMethod(paymentDto.paymentMethod())
                .build());

        if(response.paymentStatus().equals(PaymentStatus.PAYMENT_COMPLETED)) {
            orderEntity.setOrderStatus(OrderStatus.PAID);
        }
        else {
            orderEntity.setOrderStatus(OrderStatus.PAYMENT_FAILED);
        }

        var resultEntity = orderRepository.save(orderEntity);
        return orderMapper.toOrderDto(resultEntity);


    }
}
