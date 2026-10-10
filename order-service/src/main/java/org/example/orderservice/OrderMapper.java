package org.example.orderservice;

import org.example.orderservice.dto.OrderDto;
import org.example.orderservice.dto.ProductDto;
import org.example.orderservice.entity.OrderEntity;
import org.example.orderservice.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderDto toOrderDto(OrderEntity orderEntity);
    OrderEntity toOrderEntity(OrderDto orderDto);
    ProductDto toProductDto(Product product);
    Product toProductEntity(ProductDto productDto);
}
