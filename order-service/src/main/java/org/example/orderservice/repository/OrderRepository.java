package org.example.orderservice.repository;

import jdk.dynalink.linker.LinkerServices;
import org.example.orderservice.dto.OrderDto;
import org.example.orderservice.entity.OrderEntity;
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
