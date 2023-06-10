package com.example.protoFoodV2.service;

 
import com.example.protoFoodV2.dataProvider.TasteDataProvider;
import com.example.protoFoodV2.databaseModels.TasteEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@AllArgsConstructor
@Service
public class TasteManagementService {
    private final TasteDataProvider tasteDataProvider;

    public void addNewTasteRecord(TasteEntity tasteEntity) {
        tasteDataProvider.insert(tasteEntity);
        System.out.println("Added new TASTE record for user " +
                tasteEntity.getUserId() + " orderId : " + tasteEntity.getOrderId());
    }

    public Optional<TasteEntity> getTasteInfo(String orderId) {
        Optional<TasteEntity> taste = tasteDataProvider.findById(orderId);

        return taste;
    }
}
