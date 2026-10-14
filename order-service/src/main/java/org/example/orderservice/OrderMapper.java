package org.example.orderservice;

import org.example.library.order.OrderDto;
import org.example.library.order.ProductDto;
import org.example.library.order.OrderEntity;
import org.example.library.order.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderDto toOrderDto(OrderEntity orderEntity);
    OrderEntity toOrderEntity(OrderDto orderDto);
    ProductDto toProductDto(Product product);
    Product toProductEntity(ProductDto productDto);
}
