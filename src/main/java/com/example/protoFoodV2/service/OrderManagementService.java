package com.example.protoFoodV2.service;

import com.example.protoFoodV2.dataProvider.OrderDataProvider;
import com.example.protoFoodV2.databaseModels.OrderEntity;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class OrderManagementService {
    @Autowired
    private final OrderDataProvider orderDataProvider;
    public void addNewOrderRecord(OrderEntity order) {
        orderDataProvider.insert(order);
    }

    public Page<OrderEntity> getUserAllOrders(String userPhoneNumber, PageRequest pageRequest) {
        return orderDataProvider
                .findOrderEntityByUserPhoneNumber(userPhoneNumber, pageRequest);
    }
}
