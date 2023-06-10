package com.example.protoFoodV2.service;

 
import com.example.protoFoodV2.dataProvider.SkipTiffinDataProvider;
import com.example.protoFoodV2.databaseModels.SkipEntity;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class SkipTiffinManagementService {
    @Autowired
    private SkipTiffinDataProvider skipTiffinDataProvider;

    public void addSkipTiffinRecord(SkipEntity model) {
        skipTiffinDataProvider.insert(model);
        System.out.println("Added Skip-Tiffin-Record : " + model.getSkipId());
    }

    public Optional<SkipEntity> getSkipTiffinInfo(String orderId) {
        Optional<SkipEntity> skipTiffin = skipTiffinDataProvider.findById(orderId);

        return skipTiffin;
    }
}
