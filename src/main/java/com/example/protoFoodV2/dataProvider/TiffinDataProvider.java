package com.example.protoFoodV2.dataProvider;

import com.example.protoFoodV2.databaseModels.TiffinEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface TiffinDataProvider extends MongoRepository<TiffinEntity, String> {
    Optional<TiffinEntity> findTiffinEntityByUserId(String contact);

    Optional<TiffinEntity> findTiffinEntityByTiffinId(String tiffinId);

    List<TiffinEntity> findTiffinEntityByStartDateLessThanEqualAndEndDateGreaterThanEqualAndMealContaining(
            String startDate,
            String endDate,
            String meal);
}
