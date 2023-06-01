package com.example.protoFoodV2.dataProvider;

import com.example.protoFoodV2.databaseModels.PaymentEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PaymentsDataProvider extends MongoRepository<PaymentEntity, String> {
}
