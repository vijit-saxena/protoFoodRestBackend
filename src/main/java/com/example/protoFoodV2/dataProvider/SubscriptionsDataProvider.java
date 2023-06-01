package com.example.protoFoodV2.dataProvider;

import com.example.protoFoodV2.databaseModels.SubscriptionEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface SubscriptionsDataProvider extends MongoRepository<SubscriptionEntity, String> {
    Optional<SubscriptionEntity> findSubscriptionEntityBySubscriptionId(String subscriptionId);
}
