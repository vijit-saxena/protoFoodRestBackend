package com.example.protoFoodV2.service;

 
import com.example.protoFoodV2.dataProvider.TiffinDataProvider;
import com.example.protoFoodV2.databaseModels.TiffinEntity;
import com.example.protoFoodV2.exceptions.EntityNotFoundException;
import com.example.protoFoodV2.exceptions.EntityType;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class TiffinManagementService {
    @Autowired
    private final TiffinDataProvider tiffinDataProvider;

    public void addNewTiffinRecord(TiffinEntity tiffinModel) {
        tiffinDataProvider.insert(tiffinModel);
    }

    public TiffinEntity fetchUserActiveTiffin(String userPhoneNumber, String dateTime) {
        return tiffinDataProvider.findTiffinEntityByUserIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(userPhoneNumber, dateTime, dateTime)
                .orElseThrow(() -> new EntityNotFoundException(EntityType.Tiffin, dateTime));
    }

    public TiffinEntity fetchUserFutureTiffin(String userPhoneNumber, String dateTime) {
        return tiffinDataProvider.findTiffinEntityByUserIdAndStartDateGreaterThan(userPhoneNumber, dateTime)
                .orElseThrow(() -> new EntityNotFoundException(EntityType.Tiffin, dateTime));
    }

    public TiffinEntity getTiffinInfo(String tiffinId) {
        return tiffinDataProvider.findTiffinEntityByTiffinId(tiffinId)
                .orElseThrow(() -> new EntityNotFoundException(EntityType.Tiffin, tiffinId));

    }

    public List<TiffinEntity> getAllTiffinForDate(String date, String meal) {
        return tiffinDataProvider.findTiffinEntityByStartDateLessThanEqualAndEndDateGreaterThanEqualAndMealContaining(date, date, meal);
    }
}
