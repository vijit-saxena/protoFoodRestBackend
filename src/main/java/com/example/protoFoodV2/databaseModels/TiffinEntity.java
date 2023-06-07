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
    @Id private String tiffinId;
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
}
