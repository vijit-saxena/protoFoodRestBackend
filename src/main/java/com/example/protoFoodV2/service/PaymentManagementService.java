package com.example.protoFoodV2.service;

import com.example.protoFoodV2.apiModels.PaymentApiModel;
import com.example.protoFoodV2.dataProvider.PaymentsDataProvider;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class PaymentManagementService {
    private final PaymentsDataProvider paymentsDataProvider;

    public void recordNewPayment(PaymentApiModel paymentApiModel) {
        paymentsDataProvider.insert(paymentApiModel.toPaymentEntity());
        System.out.println("Recorded New Payment : " + paymentApiModel.toString());
    }
}
