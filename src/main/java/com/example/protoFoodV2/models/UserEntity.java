package com.example.protoFoodV2.models;

import lombok.Data;
import org.bson.types.ObjectId;

import java.util.List;

@Data
public class UserEntity {
//    private ObjectId userId;
    private final String name;
    private final String contact;
    private final String email;
    private List<ObjectId> location;
    private List<ObjectId> taste;
}
