package com.example.protoFoodV2.api;

import com.example.protoFoodV2.apiModels.*;
import com.example.protoFoodV2.databaseModels.LocationEntity;
import com.example.protoFoodV2.databaseModels.SubscriptionEntity;
import com.example.protoFoodV2.databaseModels.TiffinEntity;
import com.example.protoFoodV2.databaseModels.UserEntity;
import com.example.protoFoodV2.service.*;
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
    private final PaymentManagementService paymentManagementService;
    private final TasteManagementService tasteManagementService;
    private final TiffinManagementService tiffinManagementService;
    private final ExtraTiffinManagementService extraTiffinManagementService;
    private final SkipTiffinManagementService skipTiffinManagementService;

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
    @PostMapping("/addExtraTiffinRecord")
    @ResponseStatus(code = HttpStatus.OK)
    public void addExtraTiffinRecord(@RequestBody ExtraTiffinApiModel extraTiffinApiModel) {
        extraTiffinManagementService.addExtraTiffinRecord(extraTiffinApiModel);
    } // include to-from statements here

    public void viewExtraTiffinHistory() {
    }

    // SKIP-TIFFIN APIs
    @PostMapping("/addSkipTiffinRecord")
    @ResponseStatus(code = HttpStatus.OK)
    public void addSkipTiffinRecord(@RequestBody SkipTiffinApiModel skipModel) {
        skipTiffinManagementService.addSkipTiffinRecord(skipModel);
    }

    public void viewSkipTiffinHistory() {
    }

    // LOCATION APIs
    @PostMapping("/addLocation")
    @ResponseStatus(code = HttpStatus.OK)
    public void postNewLocation(@RequestBody LocationApiModel locationApiModel) {
        locationManagementService.addNewLocation(locationApiModel);
    }

    @GetMapping("/getUserActiveTiffin")
    @ResponseStatus(code = HttpStatus.OK)
    public String fetchUserActiveTiffin(@RequestParam String userPhoneNumber) {
        Optional<TiffinEntity> tiffin = tiffinManagementService.fetchUserActiveTiffin(userPhoneNumber);

        if (tiffin.isPresent()) return tiffin.get().getTiffinId();
        else return null;
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
    @PostMapping("/recordNewPayment")
    @ResponseStatus(code = HttpStatus.OK)
    public void recordNewPayment(
            @RequestBody PaymentApiModel paymentApiModel
    ) {
        paymentManagementService.recordNewPayment(paymentApiModel);
    }
    public void viewPaymentHistory() {
    }

    // TASTE APIs
    @PostMapping("/addTasteTiffinRecord")
    @ResponseStatus(code = HttpStatus.OK)
    public void addTasteRecord(@RequestBody TasteApiModel tasteApiModel) {
        tasteManagementService.addNewTasteRecord(tasteApiModel);
    }

    //TIFFIN APIs
    @PostMapping("/addTiffinRecord")
    @ResponseStatus(code = HttpStatus.OK)
    public void addTiffinRecord(@RequestBody TiffinApiModel tiffinModel) {
        tiffinManagementService.addNewTiffinRecord(tiffinModel);
    }
    public void viewCurrentTiffin() {
    }

    public void viewTiffinHistory() {
    }
}