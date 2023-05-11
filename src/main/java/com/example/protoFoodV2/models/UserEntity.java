package com.example.protoFoodV2.models;

import com.example.protoFoodV2.models.enums.Gender;
import lombok.Data;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Document
public class UserEntity {
    @Id private ObjectId userId;
    @NonNull private String firstName;
    @NonNull private String lastName;
    @NonNull private Gender gender;
    @Indexed(unique = true) private String contact;
    private String email;
    private List<ObjectId> location;
    private List<ObjectId> taste;
    @NonNull private LocalDateTime timeCreated;
}
