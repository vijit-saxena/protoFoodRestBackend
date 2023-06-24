package com.example.protoFoodV2.service;

import com.example.protoFoodV2.dataProvider.ExtraTiffinDataProvider;
import com.example.protoFoodV2.databaseModels.ExtraEntity;
import com.example.protoFoodV2.exceptions.RenderableExceptionGenerator;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ExtraTiffinManagementService {
    @Autowired
    private ExtraTiffinDataProvider extraTiffinDataProvider;

    public void addExtraTiffinRecord(ExtraEntity model) {
        extraTiffinDataProvider.insert(model);
    }

    public ExtraEntity getExtraTiffinInfo(String orderId) {
        return extraTiffinDataProvider.findById(orderId)
                .orElseThrow(() -> RenderableExceptionGenerator.generateNotAuthorizedOrNotFoundException(String.format("Extra entity with Id '%s' not found", orderId)));
    }

    public List<ExtraEntity> getAllExtrasForDate(String date, String meal) {
        return extraTiffinDataProvider.findExtraEntityByDateContainingAndMealContaining(date, meal);
    }
}
