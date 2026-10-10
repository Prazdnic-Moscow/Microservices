package org.example.orderservice.dto;

import java.math.BigDecimal;

public record ProductDto(
        String name,
        Integer count,
        BigDecimal price)
{}
