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
public class PaymentEntity {
    @Id
    @JsonProperty
    private String paymentId;
    @JsonProperty
    private String userId;
    @JsonProperty
    private String amountInRs;
    @JsonProperty
    private String orderId;
    @JsonProperty
    private String action;
    @JsonProperty
    private String timeCreated;
    @JsonProperty
    private String status;
}
