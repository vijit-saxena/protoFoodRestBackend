package com.example.protoFoodV2.service;

 
import com.example.protoFoodV2.dataProvider.SkipTiffinDataProvider;
import com.example.protoFoodV2.databaseModels.SkipEntity;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class SkipTiffinManagementService {
    @Autowired
    private SkipTiffinDataProvider skipTiffinDataProvider;

    public void addSkipTiffinRecord(SkipEntity model) {
        skipTiffinDataProvider.insert(model);
        System.out.println("Added Skip-Tiffin-Record : " + model.getSkipId());
    }
}
