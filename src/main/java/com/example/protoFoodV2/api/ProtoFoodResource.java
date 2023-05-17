package com.example.protoFoodV2.api;

import com.example.protoFoodV2.models.UserEntity;
import com.example.protoFoodV2.service.UserManagementService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("protofood")
@AllArgsConstructor
public class ProtoFoodResource {
    private final UserManagementService userManagementService;

    @GetMapping("v1/listAllUsers")
    public List<UserEntity> listAllUsers() {
        return userManagementService.listAllUsers();
    }
}
