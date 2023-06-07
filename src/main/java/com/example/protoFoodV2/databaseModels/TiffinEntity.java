package com.example.protoFoodV2.databaseModels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document
@ToString
@AllArgsConstructor
public class TiffinEntity {
    @Id private String orderId;
    private String userId;
    private String startDate;
    private String endDate;
    private String subscriptionId;
    private String locationId;
    private List<String> extras;
    private List<String> skips;
    private String meal;
    private String paymentId;
    private String timeCreated;
    private String timeUpdated;

//    public TiffinEntity(String orderId, String userId, String startDate, String endDate, String subscriptionId, String locationId, List<String> extras, List<String> skips, String meal, String paymentId, String timeCreated, String timeUpdated) {
//        this.orderId = orderId;
//        this.userId = userId;
//        this.startDate = startDate;
//        this.endDate = endDate;
//        this.subscriptionId = subscriptionId;
//        this.locationId = locationId;
//        this.extras = extras;
//        this.skips = skips;
//        this.meal = meal;
//        this.paymentId = paymentId;
//        this.timeCreated = timeCreated;
//        this.timeUpdated = timeUpdated;
//    }
}
