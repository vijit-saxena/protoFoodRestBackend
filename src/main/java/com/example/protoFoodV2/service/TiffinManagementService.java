package com.example.protoFoodV2.service;

 
import com.example.protoFoodV2.dataProvider.TiffinDataProvider;
import com.example.protoFoodV2.databaseModels.TiffinEntity;
import com.example.protoFoodV2.utils.Util;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class TiffinManagementService {
    @Autowired
    private final TiffinDataProvider tiffinDataProvider;

    public void addNewTiffinRecord(TiffinEntity tiffinModel) {
        tiffinDataProvider.insert(tiffinModel);
        System.out.println("Added Tiffin Entry : " + tiffinModel.getTiffinId());
    }

    public Optional<TiffinEntity> fetchUserActiveTiffin(String userPhoneNumber) {
        String finalUserPhoneNumber = Util.refactorPhoneNumber(userPhoneNumber);
        Optional<TiffinEntity> tiffin = tiffinDataProvider.findTiffinEntityByUserId(finalUserPhoneNumber);
        return tiffin;
    }

    public TiffinEntity getTiffinInfo(String tiffinId) {
        TiffinEntity tiffinInfo = tiffinDataProvider.findTiffinEntityByTiffinId(tiffinId);

        return tiffinInfo;
    }
}
