package com.example.protoFoodV2.service;

import com.example.protoFoodV2.apiModels.TasteApiModel;
import com.example.protoFoodV2.dataProvider.TasteDataProvider;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;

@AllArgsConstructor
@Service
public class TasteManagementService {
    private final TasteDataProvider tasteDataProvider;

    public void addNewTasteRecord(TasteApiModel tasteApiModel) {
        if (tasteApiModel.getTimeCreatedInEpochMilli() == null) {
            tasteApiModel.setTimeCreatedInEpochMilli(String.valueOf(Instant.now().toEpochMilli()));
        }
        tasteDataProvider.insert(tasteApiModel.toTasteEntity());
        System.out.println("Added new TASTE record for user " +
                tasteApiModel.getUserId() + " orderId : " + tasteApiModel.getOrderId());
    }
}
