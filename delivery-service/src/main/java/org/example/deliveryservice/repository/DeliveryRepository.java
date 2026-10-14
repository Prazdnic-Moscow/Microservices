package org.example.deliveryservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryRepository extends JpaRepository<org.example.library.order.OrderEntity, Long> {
}
