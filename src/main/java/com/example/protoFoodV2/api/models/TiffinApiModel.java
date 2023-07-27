package com.example.protoFoodV2.api.models;

import com.example.protoFoodV2.databaseModels.OrderEntity;
import com.example.protoFoodV2.databaseModels.PaymentEntity;
import com.example.protoFoodV2.databaseModels.TiffinEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
@Getter
@AllArgsConstructor
@ToString
public class TiffinApiModel {
    @JsonProperty
    private String tiffinId;
    @JsonProperty
    private String userId;
    @JsonProperty
    private String startDate;
    @JsonProperty
    private String endDate;
    @JsonProperty
    private String subscriptionId;
    @JsonProperty
    private String locationId;
    @JsonProperty
    private List<String> extras;
    @JsonProperty
    private List<String> skips;
    @JsonProperty
    private String meal;
    @JsonProperty
    private String timeCreated;
    @JsonProperty
    private String timeUpdated;
    // Payment Section
    @Id
    @JsonProperty
    private String paymentId;
    @JsonProperty
    private String amountInRs;
    @JsonProperty
    private String action;
    @JsonProperty
    private String status;

    public TiffinEntity toTiffinEntity() {
        return new TiffinEntity(
                this.tiffinId,
                this.userId,
                this.startDate,
                this.endDate,
                this.subscriptionId,
                this.locationId,
                this.extras,
                this.skips,
                this.meal,
                this.paymentId,
                this.timeCreated,
                this.timeUpdated
        );
    }

    public PaymentEntity toPaymentEntity() {
        return new PaymentEntity(
                this.paymentId,
                this.userId,
                this.amountInRs,
                this.tiffinId,
                this.action,
                this.timeCreated,
                this.status);
    }

    public OrderEntity toOrderEntity() {
        return new OrderEntity(
                this.tiffinId,
                this.userId,
                this.timeCreated
        );
    }
}
