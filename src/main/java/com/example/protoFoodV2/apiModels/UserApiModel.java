package com.example.protoFoodV2.apiModels;

import com.example.protoFoodV2.databaseModels.UserEntity;
import com.example.protoFoodV2.databaseModels.enums.Gender;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserApiModel {
    @JsonProperty("firstName")
    private String firstName;
    @JsonProperty("lastName")
    private String lastName;
    @JsonProperty("gender")
    private String gender;
    @JsonProperty("contact")
    private String contact;
    @JsonProperty("email")
    private String email;

    public UserEntity toUserEntity() {
        return new UserEntity(
                firstName,
                lastName,
                Gender.valueOf(gender),
                contact,
                email);
    }
}
