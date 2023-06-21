package com.example.protoFoodV2.service;

// 
import com.example.protoFoodV2.dataProvider.ExtraTiffinDataProvider;
import com.example.protoFoodV2.databaseModels.ExtraEntity;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ExtraTiffinManagementService {
    @Autowired
    private ExtraTiffinDataProvider extraTiffinDataProvider;

    public void addExtraTiffinRecord(ExtraEntity model) {
        extraTiffinDataProvider.insert(model);
        System.out.println("Added Extra Tiffin Entry : " + model.getExtraId());
    }

    public Optional<ExtraEntity> getExtraTiffinInfo(String orderId) {
        Optional<ExtraEntity> extraTiffin = extraTiffinDataProvider.findById(orderId);

        return extraTiffin;
    }

    public List<ExtraEntity> getAllExtrasForDate(String date, String meal) {
        List<ExtraEntity> extrasByDate = extraTiffinDataProvider.findExtraEntityByDateContainingAndMealContaining(date, meal);

        return  extrasByDate;
    }
}
