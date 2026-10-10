package org.example.orderservice.dto;

import org.example.orderservice.StatusOrder;
import org.example.orderservice.entity.Product;

import java.math.BigDecimal;
import java.util.List;

//Entity (Product, OrderEntity) — это то, как данные лежат в БД. С аннотациями JPA, связями.
//DTO — это то, как данные отдаются наружу через API. Без JPA, без связей, только нужные поля.
//Это две разные роли, и смешивать их в одном классе — плохо.

public record OrderDto (
        Long id,
        Long deliverId,
        List<ProductDto> products,
        BigDecimal allPrice,
        StatusOrder statusOrder)
{}
