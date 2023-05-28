package com.example.protoFoodV2.api;

import com.example.protoFoodV2.apiModels.LocationApiModel;
import com.example.protoFoodV2.apiModels.SubscriptionApiModel;
import com.example.protoFoodV2.apiModels.UserApiModel;
import com.example.protoFoodV2.databaseModels.SubscriptionEntity;
import com.example.protoFoodV2.databaseModels.UserEntity;
import com.example.protoFoodV2.service.LocationManagementService;
import com.example.protoFoodV2.service.SubscriptionManagementService;
import com.example.protoFoodV2.service.UserManagementService;
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
    public List<UserEntity> listAllUsers() {
        return userManagementService.listAllUsers();
    }

    // USER APIs
    @PostMapping("/user")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void createUser(@RequestBody UserApiModel userApiModel) {
        // todo : check whether user already exists, if not proceed
        System.out.println("CREATE-USER : " + userApiModel);
        userManagementService.createUser(userApiModel.toUserEntity());
    }

    public void updateUser() {
    }

    public void deleteUser() {
    }

    // SUBSCRIPTION APIs
    @PostMapping("/addSubscription") // This is an ADMIN API
    @ResponseStatus(code = HttpStatus.CREATED)
    public void postNewSubscription(@RequestBody SubscriptionApiModel subscriptionApiModel) {
        subscriptionManagementService.addNewSubscription(subscriptionApiModel);
    }

    @GetMapping("/viewSubscription")
    @ResponseStatus(code = HttpStatus.OK)
    public Optional<SubscriptionEntity> viewSubscriptionRecord(@RequestParam(name = "id") String subscriptionId) {
        Optional<SubscriptionEntity> subscriptionEntity = subscriptionManagementService.findSubscriptionById(subscriptionId);
        subscriptionEntity.ifPresentOrElse(
                s -> {
                    System.out.println("Found subscription data : " + s);
                },
                () -> {
                    System.out.println("Subscription data could not be found..");
                }
        );
        return subscriptionEntity;
    }

    public void chooseSubscription() {
    }

    @GetMapping("/listAllSubscriptions")
    @ResponseStatus(code = HttpStatus.OK)
    public List<SubscriptionEntity> listAllSubscriptions() {
        return subscriptionManagementService.listAllSubscriptionRecords();
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