package com.example.protoFoodV2;

import com.example.protoFoodV2.models.UserEntity;
import com.example.protoFoodV2.models.enums.Gender;
import com.example.protoFoodV2.repos.UsersDataProvider;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.time.LocalDateTime;

@SpringBootApplication
public class ProtoFoodV2Application {

    public static void main(String[] args) {
        SpringApplication.run(ProtoFoodV2Application.class, args);
    }

    @Bean
    CommandLineRunner runner(UsersDataProvider usersDataProvider, MongoTemplate mongoTemplate) {
        return args -> {
//			LocationEntity locationEntity = new LocationEntity(
//					"dummyBuildingName",
//					"dummyRoomNumber",
//					"dummyLat",
//					"dummyLong",
//					"dummyLandmark"
//			);

            UserEntity userEntity = new UserEntity(
                    "dummyFirstName",
                    "dummyLastName",
                    Gender.Male,
                    "dummyContact4",
                    LocalDateTime.now()
            );

            usersDataProvider.findUserEntityByContact("dummyContact4")
                    .ifPresentOrElse(user -> {
                        System.out.println("User " + user.getFirstName() + " already exists");
                    }, () -> {
                        System.out.println("Adding userEntity");
                        usersDataProvider.insert(userEntity);
                    });
        };
    }
}
