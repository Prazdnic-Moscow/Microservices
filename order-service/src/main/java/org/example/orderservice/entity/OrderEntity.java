package org.example.orderservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.orderservice.StatusOrder;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "deliver_id")
    private Long deliverId;

     @ManyToMany(cascade = CascadeType.ALL)
    private List<Product> products;

    // для денег лучше потому что нету ограничений и операции с дробями делает круто
    private BigDecimal allPrice;

    @Enumerated(EnumType.STRING)
    private StatusOrder statusOrder;





}
