package com.example.protoFoodV2.databaseModels;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@Getter
@AllArgsConstructor
@ToString
public class SubscriptionEntity {
    @Id
    @JsonProperty
    private String subscriptionId;
    @JsonProperty
    private double discountInPercent;
    @JsonProperty
    private String startDateTime;
    @JsonProperty
    private String endDateTime;
    @JsonProperty
    private Integer durationInDays;
    @JsonProperty
    private String timeCreated;
    @JsonProperty
    private String timeUpdated;
    @JsonProperty
    private String mealType;
}
