package com.example.protoFoodV2.service;

import com.example.protoFoodV2.apiModels.TiffinApiModel;
import com.example.protoFoodV2.dataProvider.TiffinDataProvider;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class TiffinManagementService {
    @Autowired
    private final TiffinDataProvider tiffinDataProvider;

    public void addNewTiffinRecord(TiffinApiModel tiffinModel) {
        tiffinDataProvider.insert(tiffinModel.toTiffinEntity());
        System.out.println("Added Tiffin Entry : " + tiffinModel.getOrderId());
    }
}
