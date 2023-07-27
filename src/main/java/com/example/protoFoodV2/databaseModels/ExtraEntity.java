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
@AllArgsConstructor
@Getter
@ToString
public class ExtraEntity {
    @JsonProperty
    @Id
    private String extraId;
    @JsonProperty
    private String userId;
    @JsonProperty
    private String tiffinId;
    @JsonProperty
    private String date;
    @JsonProperty
    private String meal;//todo : ensure this value cannot be breakfast_lunch_dinner
    @JsonProperty
    private Integer quantity;
    @JsonProperty
    private String paymentId;
    @JsonProperty
    private String timeCreated;

}
