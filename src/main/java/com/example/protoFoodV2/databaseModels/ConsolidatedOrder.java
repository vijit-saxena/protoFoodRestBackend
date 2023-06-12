package com.example.protoFoodV2.databaseModels;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConsolidatedOrder {
    private String orderId;
    private String action;
    private TasteEntity taste;
    private TiffinEntity tiffin;
    private ExtraEntity extra;
    private SkipEntity skip;
    private PaymentEntity payment;
    private String timeCreated;
}
