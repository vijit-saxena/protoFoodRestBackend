package com.example.protoFoodV2.databaseModels;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DailyTiffinEntity {
    private String userId;
    private String userName;
    private int quantity;
    private String address;
    private String latitude;
    private String longitude;
    private boolean isTaste;
}
