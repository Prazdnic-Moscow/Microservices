package org.example.paymentservice.service;

import lombok.AllArgsConstructor;
import org.example.library.payment.enumirate.PaymentMethod;
import org.example.library.payment.enumirate.PaymentStatus;
import org.example.library.payment.PaymentDto;
import org.example.library.payment.PaymentEntity;
import org.example.paymentservice.repository.PaymentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PaymentService {
    private static final Logger log = LoggerFactory.getLogger(PaymentService.class);
    private final PaymentMapper paymentMapper;
    private final PaymentRepository paymentRepository;

    public PaymentDto setStatusPayment(PaymentDto paymentDto) {

        PaymentEntity paymentEntity = paymentMapper.toPaymentEntity(paymentDto);

        if (paymentEntity.getId() != null) {
            var find = paymentRepository.findById(paymentEntity.getId());
            if (find.isPresent()) {
                log.info("Payment already exist");
                return paymentMapper.toPaymentDto(find.get());
            }
        }
        return makePayment(paymentEntity);
    }

    private PaymentDto makePayment(PaymentEntity paymentEntity) {
        if(paymentEntity.getPaymentMethod().equals(PaymentMethod.QR)){
            paymentEntity.setPaymentStatus(PaymentStatus.PAYMENT_COMPLETED);
        }
        else {
            paymentEntity.setPaymentStatus(PaymentStatus.PAYMENT_FAILED);
        }
        paymentRepository.save(paymentEntity);
        return paymentMapper.toPaymentDto(paymentEntity);
    }
}
