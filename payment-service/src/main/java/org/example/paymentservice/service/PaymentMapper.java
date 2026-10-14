package org.example.paymentservice.service;

import org.example.library.payment.PaymentDto;
import org.example.library.payment.PaymentEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentMapper {
    PaymentEntity toPaymentEntity(PaymentDto paymentDto);
    PaymentDto toPaymentDto(PaymentEntity paymentEntity);
}
