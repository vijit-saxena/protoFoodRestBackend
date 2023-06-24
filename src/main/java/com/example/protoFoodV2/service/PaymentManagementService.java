package com.example.protoFoodV2.service;

 
import com.example.protoFoodV2.dataProvider.PaymentsDataProvider;
import com.example.protoFoodV2.databaseModels.PaymentEntity;
import com.example.protoFoodV2.exceptions.RenderableExceptionGenerator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class PaymentManagementService {
    private final PaymentsDataProvider paymentsDataProvider;

    public void recordNewPayment(PaymentEntity paymentEntity) {
        paymentsDataProvider.insert(paymentEntity);
    }

    public PaymentEntity getPaymentInfo(String orderId) {
        return paymentsDataProvider.findByOrderId(orderId)
                .orElseThrow(() -> RenderableExceptionGenerator.generateNotAuthorizedOrNotFoundException(String.format("Payment entity with Id: %s not found", orderId)));
    }
}
