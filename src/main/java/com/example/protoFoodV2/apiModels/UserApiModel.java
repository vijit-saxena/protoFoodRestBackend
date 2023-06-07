package com.example.protoFoodV2.apiModels;

import com.example.protoFoodV2.databaseModels.UserEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class UserApiModel {
    @JsonProperty
    private String firstName;
    @JsonProperty
    private String lastName;
    @JsonProperty
    private String gender;
    @JsonProperty // without country code
    private String contact;
    @JsonProperty
    private String email;
    @JsonProperty
    private String timeCreated;

    public UserEntity toUserEntity() {
        return new UserEntity(
                firstName,
                lastName,
                gender,
                contact,
                email,
                timeCreated);
    }
}
