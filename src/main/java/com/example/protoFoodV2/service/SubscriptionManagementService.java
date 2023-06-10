package com.example.protoFoodV2.service;

 
import com.example.protoFoodV2.databaseModels.SubscriptionEntity;
import com.example.protoFoodV2.dataProvider.SubscriptionsDataProvider;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class SubscriptionManagementService {
    private final SubscriptionsDataProvider subscriptionsDataProvider;

    public void addNewSubscription(SubscriptionEntity subscriptionEntity) {
        subscriptionsDataProvider.insert(subscriptionEntity);
        System.out.println("Added new subscription : " + subscriptionEntity);
    }

    public Optional<SubscriptionEntity> findSubscriptionById(String subscriptionId) {
        return subscriptionsDataProvider.findSubscriptionEntityBySubscriptionId(subscriptionId);
    }

    public List<SubscriptionEntity> listAllSubscriptionRecords() {
        return subscriptionsDataProvider.findAll();
    }

    public List<SubscriptionEntity> listActiveSubscriptionRecords() {
        List<SubscriptionEntity> allSubscriptions = subscriptionsDataProvider.findAll();
        List<SubscriptionEntity> activeSubscriptions = new ArrayList<>();
        LocalDateTime today = LocalDateTime.now();
        for (SubscriptionEntity subscription : allSubscriptions) {
            if (!(today.isBefore(LocalDateTime.parse(subscription.getStartDateTime())) ||
                    today.isAfter(LocalDateTime.parse(subscription.getEndDateTime())))) {
                activeSubscriptions.add(subscription);
            }
        }
        return activeSubscriptions;
    }
}
