package com.example.protoFoodV2.api;

import com.example.protoFoodV2.apiModels.LocationApiModel;
import com.example.protoFoodV2.apiModels.SubscriptionApiModel;
import com.example.protoFoodV2.apiModels.UserApiModel;
import com.example.protoFoodV2.databaseModels.LocationEntity;
import com.example.protoFoodV2.databaseModels.SubscriptionEntity;
import com.example.protoFoodV2.databaseModels.UserEntity;
import com.example.protoFoodV2.service.LocationManagementService;
import com.example.protoFoodV2.service.SubscriptionManagementService;
import com.example.protoFoodV2.service.UserManagementService;
import com.example.protoFoodV2.utils.Util;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("protofood/v1")
@AllArgsConstructor
public class ProtoFoodResource {
    private final UserManagementService userManagementService;
    private final SubscriptionManagementService subscriptionManagementService;
    private final LocationManagementService locationManagementService;

    @GetMapping("/listAllUsers")
    @ResponseStatus(code = HttpStatus.OK)
    public List<UserEntity> listAllUsers() {
        return userManagementService.listAllUsers();
    }

    // USER APIs
    @PostMapping("/createUser")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void createUser(@RequestBody UserApiModel userApiModel) {
        try {
            userManagementService.userAlreadyExists(userApiModel.getContact());
        } catch (Exception e) {
            System.out.println("Exception Raised : " + e.getClass());
            throw e;
        }

        userManagementService.createUser(userApiModel.toUserEntity());
    }

    public void updateUser() {
    }

    public void deleteUser() {
    }

    @GetMapping("/getUser")
    @ResponseStatus(code = HttpStatus.OK)
    public Optional<UserEntity> getUser(
            @RequestParam(name = "userPhoneNumber") String userPhoneNumber) {
        Optional<UserEntity> user = userManagementService.getUserByPhoneNumber(userPhoneNumber);
        return user;
    }

    // SUBSCRIPTION APIs
    @PostMapping("/addSubscription") // This is an ADMIN API
    @ResponseStatus(code = HttpStatus.CREATED)
    public void postNewSubscription(@RequestBody SubscriptionApiModel subscriptionApiModel) {
        subscriptionManagementService.addNewSubscription(subscriptionApiModel);
    }

    @GetMapping("/viewSubscription") // This is an ADMIN API
    @ResponseStatus(code = HttpStatus.OK)
    public Optional<SubscriptionEntity> viewSubscriptionRecord(
            @RequestParam(name = "id") String subscriptionId) {
        Optional<SubscriptionEntity> subscriptionEntity =
                subscriptionManagementService.findSubscriptionById(subscriptionId);
        subscriptionEntity.ifPresentOrElse(
                data -> {
                    System.out.println("Found subscription data : " + data);
                },
                () -> {
                    System.out.println("Subscription data could not be found..");
                }
        );
        return subscriptionEntity;
    }

    public void chooseSubscription() {
    }

    @GetMapping("/listAllSubscriptions") // This is an ADMIN API
    @ResponseStatus(code = HttpStatus.OK)
    public List<SubscriptionEntity> listAllSubscriptions() {
        return subscriptionManagementService.listAllSubscriptionRecords();
    }

    @GetMapping("/listActiveSubscriptions")
    @ResponseStatus(code = HttpStatus.OK)
    public List<SubscriptionEntity> listActiveSubscriptions() {
        List<SubscriptionEntity> activeSubscriptions =
                subscriptionManagementService.listActiveSubscriptionRecords();
        return activeSubscriptions;
    }

    // EXTRA-TIFFIN APIs
    public void addExtraTiffin() {
    } // include to-from statements here

    public void viewExtraTiffinHistory() {
    }

    // SKIP-TIFFIN APIs
    public void addSkipTiffin() {
    }

    public void viewSkipTiffinHistory() {
    }

    // LOCATION APIs
    @PostMapping("/addLocation")
    @ResponseStatus(code = HttpStatus.OK)
    public void postNewLocation(@RequestBody LocationApiModel locationApiModel) {
        locationManagementService.addNewLocation(locationApiModel);
    }

    public void deleteLocation() {
    }

    public void updateLocation() {
    }

    @GetMapping("/fetchUserClosestLocation")
    @ResponseStatus(code = HttpStatus.OK)
    public Optional<LocationEntity> fetchUserClosestLocation(
            @RequestParam(name = "latitude") double latitude,
            @RequestParam(name = "longitude") double longitude,
            @RequestParam(name = "userPhoneNumber") String userPhoneNumber) {
        Optional<LocationEntity> closestUserLocation = locationManagementService.fetchClosestLocation(latitude, longitude, userPhoneNumber);
        return closestUserLocation;
    }

    // PAYMENT APIs
    public void viewPaymentHistory() {
    }

    // TASTE APIs
    public void addTasteTiffin() {
    }

    //TIFFIN APIs
    public void viewCurrentTiffin() {
    }

    public void viewTiffinHistory() {
    }
}