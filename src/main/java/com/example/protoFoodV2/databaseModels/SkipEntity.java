package com.example.protoFoodV2.databaseModels;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document
@Getter
@AllArgsConstructor
public class SkipEntity {
    @Id
    @JsonProperty
    private String skipId;
    @JsonProperty
    private String userId;
    @JsonProperty
    private String date;
    @JsonProperty
    private String meal;// ensure this value cannot be breakfast_lunch_dinner
    @JsonProperty
    private String tiffinId;
    @JsonProperty
    private String timeCreated;
}
