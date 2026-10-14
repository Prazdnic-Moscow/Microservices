package org.example.orderservice.repository;
import org.example.library.order.OrderEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    @Query("""
            select or from OrderEntity or
            where or.id = :id
            """)
    List<OrderEntity> getAllOrdersById(@Param("id") Long Id,
                                   Pageable pageable);
}
