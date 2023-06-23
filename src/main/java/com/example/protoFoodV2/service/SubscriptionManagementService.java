package com.example.protoFoodV2.service;

import com.example.protoFoodV2.databaseModels.SubscriptionEntity;
import com.example.protoFoodV2.dataProvider.SubscriptionsDataProvider;
import com.example.protoFoodV2.exceptions.EntityNotFoundException;
import com.example.protoFoodV2.exceptions.EntityType;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Service
public class SubscriptionManagementService {
    private final SubscriptionsDataProvider subscriptionsDataProvider;

    public void addNewSubscription(SubscriptionEntity subscriptionEntity) {
        subscriptionsDataProvider.insert(subscriptionEntity);
    }

    public SubscriptionEntity findSubscriptionById(String subscriptionId) {
        return subscriptionsDataProvider
                .findSubscriptionEntityBySubscriptionId(subscriptionId)
                .orElseThrow(() -> new EntityNotFoundException(EntityType.Subscription, subscriptionId));
    }

    public List<SubscriptionEntity> listAllSubscriptionRecords() {
        return subscriptionsDataProvider.findAll();
    }

    public List<SubscriptionEntity> listActiveSubscriptionRecords() {
        String currentDateTime = LocalDateTime.now().toString();
        return subscriptionsDataProvider.findSubscriptionEntityByStartDateTimeLessThanEqualAndEndDateTimeGreaterThanEqual(currentDateTime, currentDateTime);
    }
}