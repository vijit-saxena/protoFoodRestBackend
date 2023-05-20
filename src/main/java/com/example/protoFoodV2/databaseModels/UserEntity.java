package com.example.protoFoodV2.databaseModels;

import com.example.protoFoodV2.databaseModels.enums.Gender;
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
    @Id private ObjectId userId;
    @NonNull private String firstName;
    @NonNull private String lastName;
    @NonNull private Gender gender;
    @Indexed(unique = true) private String contact;
    private String email;
    private List<ObjectId> location;
    private List<ObjectId> taste;
    @NonNull private LocalDateTime timeCreated;

    public UserEntity(@NonNull String firstName, @NonNull String lastName, @NonNull Gender gender, String contact, String email) {
        this(firstName, lastName, gender, contact, email, LocalDateTime.now());
    }

    public UserEntity(@NonNull String firstName, @NonNull String lastName, @NonNull Gender gender, String contact, String email, @NonNull LocalDateTime timeCreated) {
        this(firstName, lastName, gender, contact, email, null, timeCreated);
    }

    public UserEntity(@NonNull String firstName, @NonNull String lastName, @NonNull Gender gender, String contact, String email, List<ObjectId> location, @NonNull LocalDateTime timeCreated) {
        this(firstName, lastName, gender, contact, email, location, null, timeCreated);
    }

    @PersistenceCreator
    public UserEntity(@NonNull String firstName, @NonNull String lastName, @NonNull Gender gender, String contact, String email, List<ObjectId> location, List<ObjectId> taste, @NonNull LocalDateTime timeCreated) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.contact = contact;
        this.email = email;
        this.location = location;
        this.taste = taste;
        this.timeCreated = timeCreated;
    }
}
