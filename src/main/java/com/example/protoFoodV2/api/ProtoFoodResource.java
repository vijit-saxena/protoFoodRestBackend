package com.example.protoFoodV2.api;

 
import com.example.protoFoodV2.databaseModels.*;
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
    private final OrderManagementService orderManagementService;

    @GetMapping("/listAllUsers")
    @ResponseStatus(code = HttpStatus.OK)
    public List<UserEntity> listAllUsers() {
        return userManagementService.listAllUsers();
    }

    // USER APIs
    @PostMapping("/createUser")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void createUser(@RequestBody UserEntity userEntity) {
        try {
            userManagementService.userAlreadyExists(userEntity.getContact());
        } catch (Exception e) {
            System.out.println("Exception Raised : " + e.getClass());
            throw e;
        }

        userManagementService.createUser(userEntity);
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
    public void postNewSubscription(@RequestBody SubscriptionEntity subscriptionEntity) {
        subscriptionManagementService.addNewSubscription(subscriptionEntity);
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
    public void addExtraTiffinRecord(@RequestBody ExtraEntity extraTiffinEntity) {
        extraTiffinManagementService.addExtraTiffinRecord(extraTiffinEntity);
    } // include to-from statements here

    public void viewExtraTiffinHistory() {
    }

    // SKIP-TIFFIN APIs
    @PostMapping("/addSkipTiffinRecord")
    @ResponseStatus(code = HttpStatus.OK)
    public void addSkipTiffinRecord(@RequestBody SkipEntity skipModel) {
        skipTiffinManagementService.addSkipTiffinRecord(skipModel);
    }

    public void viewSkipTiffinHistory() {
    }

    // LOCATION APIs
    @PostMapping("/addLocation")
    @ResponseStatus(code = HttpStatus.OK)
    public void postNewLocation(@RequestBody LocationEntity locationEntity) {
        locationManagementService.addNewLocation(locationEntity);
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
            @RequestBody PaymentEntity paymentEntity
    ) {
        paymentManagementService.recordNewPayment(paymentEntity);
    }
    public void viewPaymentHistory() {
    }

    // TASTE APIs
    @PostMapping("/addTasteTiffinRecord")
    @ResponseStatus(code = HttpStatus.OK)
    public void addTasteRecord(@RequestBody TasteEntity tasteEntity) {
        tasteManagementService.addNewTasteRecord(tasteEntity);
    }

    //TIFFIN APIs
    @PostMapping("/addTiffinRecord")
    @ResponseStatus(code = HttpStatus.OK)
    public void addTiffinRecord(@RequestBody TiffinEntity tiffinModel) {
        tiffinManagementService.addNewTiffinRecord(tiffinModel);
    }


    @GetMapping("/getUserActiveTiffin")
    @ResponseStatus(code = HttpStatus.OK)
    public String fetchUserActiveTiffin(@RequestParam String userPhoneNumber) {
        Optional<TiffinEntity> tiffin = tiffinManagementService.fetchUserActiveTiffin(userPhoneNumber);

        if (tiffin.isPresent()) return tiffin.get().getTiffinId();
        else return null;
    }

    @GetMapping("/getTiffinInfo")
    public TiffinEntity getTiffinInfo(@RequestParam String tiffinId) {
        TiffinEntity tiffinInfo = tiffinManagementService.getTiffinInfo(tiffinId);

        return tiffinInfo;
    }

    public void viewTiffinHistory() {
    }

    // ORDER APIs
    @PostMapping("/addNewOrderRecord")
    @ResponseStatus(code = HttpStatus.OK)
    public void addNewOrderRecord(@RequestBody OrderEntity order) {
        orderManagementService.addNewOrderRecord(order);
    }
}