package com.example.protoFoodV2.dataProvider;

import com.example.protoFoodV2.databaseModels.SubscriptionEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface SubscriptionsDataProvider extends MongoRepository<SubscriptionEntity, String> {
    Optional<SubscriptionEntity> findSubscriptionEntityBySubscriptionId(String subscriptionId);

    List<SubscriptionEntity> findSubscriptionEntityByStartDateTimeLessThanEqualAndEndDateTimeGreaterThanEqual(String startDateTime, String endDateTime);
}
