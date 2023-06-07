package com.example.protoFoodV2.apiModels;

import com.example.protoFoodV2.databaseModels.SkipEntity;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SkipTiffinApiModel {
    @JsonProperty
    private String skipId;
    @JsonProperty
    private String userId;
    @JsonProperty
    private String date;
    @JsonProperty
    private String meal;
    @JsonProperty
    private String tiffinId;
        @JsonProperty
        private String timeCreated;

        public SkipEntity toSkipEntity() {
        return new SkipEntity(skipId, userId, date, meal, tiffinId, timeCreated);
    }
}
