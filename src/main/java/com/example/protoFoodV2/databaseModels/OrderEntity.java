package com.example.protoFoodV2.databaseModels;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@AllArgsConstructor
@Getter
public class OrderEntity {
    @Id
    @JsonProperty
    private String orderId;
    @JsonProperty
    private String userPhoneNumber;
    @JsonProperty
    private String timeCreated;
}
