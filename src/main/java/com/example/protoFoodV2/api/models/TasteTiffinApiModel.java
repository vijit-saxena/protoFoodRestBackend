package com.example.protoFoodV2.api.models;

import com.example.protoFoodV2.databaseModels.OrderEntity;
import com.example.protoFoodV2.databaseModels.PaymentEntity;
import com.example.protoFoodV2.databaseModels.TasteEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

@Data
@Getter
@AllArgsConstructor
@ToString
public class TasteTiffinApiModel {
    // Taste Tiffin Section
    @JsonProperty
    private String tasteId;
    @JsonProperty
    private String userId;
    @JsonProperty
    private String date;
    @JsonProperty
    private String meal;
    @JsonProperty
    private Integer quantity;
    @JsonProperty
    private String locationId;
    @JsonProperty
    private String timeCreated;
    // Payments Section
    @JsonProperty
    private String paymentId;
    @JsonProperty
    private String amountInRs;
    @JsonProperty
    private String action;
    @JsonProperty
    private String status;

    public TasteEntity toTasteEntity() {
        return new TasteEntity(
                this.tasteId,
                this.userId,
                this.date,
                this.meal,
                this.quantity,
                this.paymentId,
                this.locationId,
                this.timeCreated);
    }

    public PaymentEntity toPaymentEntity() {
        return new PaymentEntity(
                this.paymentId,
                this.userId,
                this.amountInRs,
                this.tasteId,
                this.action,
                this.timeCreated,
                this.status);
    }

    public OrderEntity toOrderEntity() {
        return new OrderEntity(
                this.tasteId,
                this.userId,
                this.timeCreated
        );
    }
}
