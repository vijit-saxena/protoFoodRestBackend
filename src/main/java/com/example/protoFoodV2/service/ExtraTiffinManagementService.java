package com.example.protoFoodV2.service;

// 
import com.example.protoFoodV2.dataProvider.ExtraTiffinDataProvider;
import com.example.protoFoodV2.databaseModels.ExtraEntity;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ExtraTiffinManagementService {
    @Autowired
    private ExtraTiffinDataProvider extraTiffinDataProvider;

    public void addExtraTiffinRecord(ExtraEntity model) {
        extraTiffinDataProvider.insert(model);
        System.out.println("Added Extra Tiffin Entry : " + model.getExtraId());
    }
}
