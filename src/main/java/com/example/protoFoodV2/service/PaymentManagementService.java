package com.example.protoFoodV2.service;

 
import com.example.protoFoodV2.dataProvider.PaymentsDataProvider;
import com.example.protoFoodV2.databaseModels.PaymentEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class PaymentManagementService {
    private final PaymentsDataProvider paymentsDataProvider;

    public void recordNewPayment(PaymentEntity paymentEntity) {
        paymentsDataProvider.insert(paymentEntity);
        System.out.println("Recorded New Payment : " + paymentEntity.toString());
    }

    public Optional<PaymentEntity> getPaymentInfo(String orderId) {
        Optional<PaymentEntity> payment = paymentsDataProvider.findByOrderId(orderId);

        return payment;
    }
}
