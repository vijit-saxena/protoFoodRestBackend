package com.example.protoFoodV2.service;

 
import com.example.protoFoodV2.dataProvider.SkipTiffinDataProvider;
import com.example.protoFoodV2.databaseModels.SkipEntity;
import com.example.protoFoodV2.exceptions.RenderableExceptionGenerator;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class SkipTiffinManagementService {
    @Autowired
    private SkipTiffinDataProvider skipTiffinDataProvider;

    public void addSkipTiffinRecord(SkipEntity model) {
        skipTiffinDataProvider.insert(model);
    }

    public SkipEntity getSkipTiffinInfo(String orderId) {
        return skipTiffinDataProvider.findById(orderId)
                .orElseThrow(() -> RenderableExceptionGenerator.generateNotAuthorizedOrNotFoundException(String.format("SkipTiffin entity with id: %s not found", orderId)));
    }

    public List<SkipEntity> getAllSkipsForDate(String date, String meal) {
        return skipTiffinDataProvider.findSkipEntityByDateContainingAndMealContaining(date, meal);
    }
}
