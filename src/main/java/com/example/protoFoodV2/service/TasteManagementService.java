package com.example.protoFoodV2.service;

 
import com.example.protoFoodV2.dataProvider.TasteDataProvider;
import com.example.protoFoodV2.databaseModels.TasteEntity;
import com.example.protoFoodV2.exceptions.RenderableExceptionGenerator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class TasteManagementService {
    private final TasteDataProvider tasteDataProvider;

    public void addNewTasteRecord(TasteEntity tasteEntity) {
        tasteDataProvider.insert(tasteEntity);
    }

    public TasteEntity getTasteInfo(String orderId) {
        return tasteDataProvider.findById(orderId)
                .orElseThrow(() -> RenderableExceptionGenerator.generateNotAuthorizedOrNotFoundException(String.format("Taste entity with Id %s not found", orderId)));
    }

    public List<TasteEntity> getAllTastesForDate(String date, String meal) {
        return tasteDataProvider.findTasteEntityByDateContainingAndMealContaining(date, meal);
    }
}
