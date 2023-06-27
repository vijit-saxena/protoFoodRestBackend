package com.example.protoFoodV2.databaseModels;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@AllArgsConstructor
@Getter
@ToString
public class TasteEntity {
    @Id
    @JsonProperty
    private String orderId;
    @JsonProperty
    private String userId;
    @JsonProperty
    private String date;
    @JsonProperty
    private String meal;
    @JsonProperty
    private Integer quantity;
    @JsonProperty
    private String paymentId;
    @JsonProperty
    private String locationId;
    @JsonProperty
    private String timeCreated;
}
