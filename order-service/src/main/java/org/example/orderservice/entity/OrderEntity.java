package org.example.orderservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private Long deliverId;

     @ManyToMany
    private List<Product> products;

    // для денег лучше потому что нету ограничений и операции с дробями делает круто
    private BigDecimal price;





}
