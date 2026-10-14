package org.example.deliveryservice.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.deliveryservice.repository.DeliveryRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class DeliverService {
    public final DeliveryRepository deliveryRepository;
    public final OrderMapper orderMapper;

    public org.example.library.order.OrderDto startingDeliver(Long id) {
        log.info("inside method startingDeliver");

        var entity = deliveryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Order not found by id " + id));

        entity.setDeliverId(999L);

        var saved = deliveryRepository.save(entity);
        return orderMapper.toOrderDto(saved);
    }
}
