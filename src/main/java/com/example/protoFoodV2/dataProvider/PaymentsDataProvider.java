package com.example.protoFoodV2.dataProvider;

import com.example.protoFoodV2.databaseModels.PaymentEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface PaymentsDataProvider extends MongoRepository<PaymentEntity, String> {
    Optional<PaymentEntity> findByOrderId(String orderId);
}
