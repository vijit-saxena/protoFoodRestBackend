package com.example.protoFoodV2.service;

import com.example.protoFoodV2.databaseModels.SubscriptionEntity;
import com.example.protoFoodV2.repos.SubscriptionsDataProvider;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class SubscriptionManagementService {
    private final SubscriptionsDataProvider subscriptionsDataProvider;

    public void addNewSubscription(SubscriptionEntity subscriptionEntity) {
        subscriptionsDataProvider.insert(subscriptionEntity);
        System.out.println("Added new subscription : " + subscriptionEntity.getSubscriptionId());
    }

    public Optional<SubscriptionEntity> findSubscriptionById(String subscriptionId) {
        return subscriptionsDataProvider.findSubscriptionEntityBySubscriptionId(new ObjectId(subscriptionId));
    }

    public List<SubscriptionEntity> listAllSubscriptionRecords() {
        return subscriptionsDataProvider.findAll();
    }
}
