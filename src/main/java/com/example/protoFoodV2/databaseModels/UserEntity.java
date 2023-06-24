package com.example.protoFoodV2.databaseModels;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@AllArgsConstructor
@Getter
@ToString
public class UserEntity {
    @Id
    @JsonProperty
    private String userId;
    @JsonProperty
    private String firstName;
    @JsonProperty
    private String lastName;
    @JsonProperty
    private String gender;
    @JsonProperty
    @Indexed(unique = true)
    private String contact;
    @JsonProperty
    private String email;
    @JsonProperty
    private String timeCreated;
}
