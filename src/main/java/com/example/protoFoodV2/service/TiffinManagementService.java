package com.example.protoFoodV2.service;

 
import com.example.protoFoodV2.dataProvider.TiffinDataProvider;
import com.example.protoFoodV2.databaseModels.TiffinEntity;
import com.example.protoFoodV2.utils.Util;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
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

    public Optional<TiffinEntity> getTiffinInfo(String tiffinId) {
        Optional<TiffinEntity> tiffinInfo = tiffinDataProvider.findTiffinEntityByTiffinId(tiffinId);

        return tiffinInfo;
    }

    public List<TiffinEntity> getAllTiffinForDate(String date, String meal) {
        List<TiffinEntity> tiffinByDate = tiffinDataProvider.findTiffinEntityByStartDateLessThanEqualAndEndDateGreaterThanEqualAndMealContaining(date, date, meal);

        return tiffinByDate;
    }
}
