package com.example.protoFoodV2.databaseModels;

import lombok.Data;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Document
public class UserEntity {
    @Id private String userId;
    @NonNull private String firstName;
    @NonNull private String lastName;
    @NonNull private String gender;
    @Indexed(unique = true) private String contact;
    private String email;
    @NonNull private String timeCreated;

    @PersistenceCreator
    public UserEntity(@NonNull String firstName, @NonNull String lastName, @NonNull String gender, String contact, String email, @NonNull String timeCreated) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.contact = contact;
        this.email = email;
        this.timeCreated = timeCreated;
    }
}
