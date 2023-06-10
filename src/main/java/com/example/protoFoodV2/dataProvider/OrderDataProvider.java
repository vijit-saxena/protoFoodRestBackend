package com.example.protoFoodV2.dataProvider;

import com.example.protoFoodV2.databaseModels.OrderEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderDataProvider extends MongoRepository<OrderEntity, String> {
}
