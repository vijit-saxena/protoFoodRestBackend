package com.example.protoFoodV2.service;

import com.example.protoFoodV2.apiModels.SkipTiffinApiModel;
import com.example.protoFoodV2.dataProvider.SkipTiffinDataProvider;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class SkipTiffinManagementService {
    @Autowired
    private SkipTiffinDataProvider skipTiffinDataProvider;

    public void addSkipTiffinRecord(SkipTiffinApiModel model) {
        skipTiffinDataProvider.insert(model.toSkipEntity());
        System.out.println("Added Skip-Tiffin-Record : " + model.toSkipEntity().getSkipId());
    }
}
