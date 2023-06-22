package com.example.protoFoodV2.api;

import com.example.protoFoodV2.databaseModels.*;
import com.example.protoFoodV2.service.*;
import com.example.protoFoodV2.utils.UserAction;
import com.example.protoFoodV2.utils.Util;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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

    private final int PAGE_SIZE = 10;

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

    @GetMapping("/fetchUserAllLocations/{userPhoneNumber}")
    @ResponseStatus(code = HttpStatus.OK)
    public List<LocationEntity> fetchUserAllLocations(@PathVariable String userPhoneNumber) {
        String parsedPhoneNumber = Util.refactorPhoneNumber(userPhoneNumber);
        List<LocationEntity> userAllLocations = locationManagementService.fetchUserAllLocations(parsedPhoneNumber);

        return userAllLocations;
    }

    @GetMapping("/getLocation/{locationId}")
    @ResponseStatus(code = HttpStatus.OK)
    public LocationEntity getLocation(@PathVariable String locationId) {
        return locationManagementService.getLocationById(locationId);
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
    public Optional<TiffinEntity> fetchUserActiveTiffin(
            @RequestParam String userPhoneNumber,
            @RequestParam String dateTime) {

        return tiffinManagementService.fetchUserActiveTiffin(userPhoneNumber, dateTime);
    }

    @GetMapping("/getUserFutureTiffin")
    @ResponseStatus(code = HttpStatus.OK)
    public Optional<TiffinEntity> fetchUserFutureTiffin(
            @RequestParam String userPhoneNumber,
            @RequestParam String dateTime) {

        return tiffinManagementService.fetchUserFutureTiffin(userPhoneNumber, dateTime);
    }

    @GetMapping("/getTiffinInfo")
    public Optional<TiffinEntity> getTiffinInfo(@RequestParam String tiffinId) {
        Optional<TiffinEntity> tiffinInfo = tiffinManagementService.getTiffinInfo(tiffinId);

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

    @GetMapping("/getUserAllOrders/{userPhoneNumber}")
    @ResponseStatus(code = HttpStatus.OK)
    public List<ConsolidatedOrder> getUserAllConsolidatedOrders(@PathVariable String userPhoneNumber,
                                                    @RequestParam(defaultValue = "0") int pageNumber) throws NotFoundException {
        String parsedPhoneNumber = Util.refactorPhoneNumber(userPhoneNumber);
        PageRequest pageRequest = PageRequest.of(pageNumber, PAGE_SIZE, Sort.by("timeCreated").descending());
System.out.println("Phone number is : " + parsedPhoneNumber);
        Page<OrderEntity> paginatedOrders = orderManagementService.getUserAllOrders(parsedPhoneNumber, pageRequest);
        List<OrderEntity> orderList = paginatedOrders.getContent();
        System.out.println("Size is : " + orderList.size());

        List<ConsolidatedOrder> consolidatedOrdersList = new ArrayList<>();
        for (OrderEntity order : orderList) {
            Optional<TiffinEntity> tiffin = tiffinManagementService.getTiffinInfo(order.getOrderId());
            Optional<TasteEntity> taste = tasteManagementService.getTasteInfo(order.getOrderId());
            Optional<ExtraEntity> extra = extraTiffinManagementService.getExtraTiffinInfo(order.getOrderId());
            Optional<SkipEntity> skip = skipTiffinManagementService.getSkipTiffinInfo(order.getOrderId());
            Optional<PaymentEntity> payment = paymentManagementService.getPaymentInfo(order.getOrderId());

            ConsolidatedOrder consolidatedOrder = new ConsolidatedOrder();
            consolidatedOrder.setOrderId(order.getOrderId());
            consolidatedOrder.setTimeCreated(order.getTimeCreated());
            if (taste.isPresent()) {
                consolidatedOrder.setTaste(taste.get());
                consolidatedOrder.setAction(UserAction.Taste.name());
            } else if (tiffin.isPresent()) {
                consolidatedOrder.setTiffin(tiffin.get());
                consolidatedOrder.setAction(UserAction.Tiffin.name());
            } else if (extra.isPresent()) {
                consolidatedOrder.setExtra(extra.get());
                consolidatedOrder.setAction(UserAction.ExtraTiffin.name());
            } else if (skip.isPresent()) {
                consolidatedOrder.setSkip(skip.get());
                consolidatedOrder.setAction(UserAction.SkipTiffin.name());
            }

            if (payment.isPresent()) {
                consolidatedOrder.setPayment(payment.get());
            }
            consolidatedOrdersList.add(consolidatedOrder); // Todo : Validate consolidatedOrder is not null.
        }

        return consolidatedOrdersList;
    }

    // DAILY TIFFIN LIST OPERATIONS
    @GetMapping("/fetchAllTiffin")
    @ResponseStatus(code = HttpStatus.OK)
    public List<TiffinEntity> fetchAllTiffin(
            @NonNull @RequestParam String date,
            @NonNull @RequestParam String meal) {

        return tiffinManagementService.getAllTiffinForDate(date, meal);
    }

    @GetMapping("/fetchAllExtras")
    @ResponseStatus(code = HttpStatus.OK)
    public List<ExtraEntity> fetchAllExtras(
            @NonNull @RequestParam String date,
            @NonNull @RequestParam String meal) {

        return extraTiffinManagementService.getAllExtrasForDate(date, meal);
    }

    @GetMapping("/fetchAllSkips")
    @ResponseStatus(code = HttpStatus.OK)
    public List<SkipEntity> fetchAllSkips(
            @NonNull @RequestParam String date,
            @NonNull @RequestParam String meal) {

        return skipTiffinManagementService.getAllSkipsForDate(date, meal);
    }

    @GetMapping("/fetchAllTastes")
    @ResponseStatus(code = HttpStatus.OK)
    public List<TasteEntity> fetchAllTastes(
            @NonNull @RequestParam String date,
            @NonNull @RequestParam String meal) {

        return tasteManagementService.getAllTastesForDate(date, meal);
    }

    @GetMapping("/generateDailyTiffinReport")
    @ResponseStatus(code = HttpStatus.OK)
    public List<DailyTiffinEntity> generateDailyTiffinReport(
            @NonNull @RequestParam String date,
            @NonNull @RequestParam String meal) {
        List<TiffinEntity> tiffinForDate = fetchAllTiffin(date, meal);
        List<ExtraEntity> extrasForDate = fetchAllExtras(date, meal);
        List<SkipEntity> skipsForDate = fetchAllSkips(date, meal);
        List<TasteEntity> tastesForDate = fetchAllTastes(date, meal);

        List<DailyTiffinEntity> dailyTiffinEntityList = new ArrayList<>();

        Map<String, Integer> userTiffinCount = new HashMap<>();
        for (TiffinEntity tiffin : tiffinForDate) {
            userTiffinCount.put(tiffin.getUserId(), 1);
        }

        for (ExtraEntity extra : extrasForDate) {
            int count = userTiffinCount.get(extra.getUserId()) + extra.getQuantity();
            userTiffinCount.put(extra.getUserId(), count);
        }

        for (SkipEntity skip : skipsForDate) {
            int count = userTiffinCount.get(skip.getUserId()) - 1;
            userTiffinCount.put(skip.getUserId(), count);
        }

        for (TiffinEntity tiffin : tiffinForDate) {
            DailyTiffinEntity dailyTiffinEntity = new DailyTiffinEntity();
            dailyTiffinEntity.setUserId(tiffin.getUserId());
            dailyTiffinEntity.setQuantity(userTiffinCount.get(tiffin.getUserId()));

            UserEntity user = getUser(tiffin.getUserId()).get();
            dailyTiffinEntity.setUserName(user.getFirstName() +  " " + user.getLastName());

            LocationEntity location = getLocation(tiffin.getLocationId());
            dailyTiffinEntity.setAddress(Util.generateAddressFromLocationEntity(location));
            dailyTiffinEntity.setLatitude(location.getLatitude());
            dailyTiffinEntity.setLongitude(location.getLongitude());

            dailyTiffinEntity.setTaste(false);
            dailyTiffinEntityList.add(dailyTiffinEntity);
        }
        for(TasteEntity taste : tastesForDate) {
            DailyTiffinEntity dailyTiffinEntity = new DailyTiffinEntity();
            dailyTiffinEntity.setUserId(taste.getUserId());
            dailyTiffinEntity.setQuantity(taste.getQuantity());

            LocationEntity location = getLocation(taste.getLocationId());
            dailyTiffinEntity.setAddress(Util.generateAddressFromLocationEntity(location));
            dailyTiffinEntity.setLatitude(location.getLatitude());
            dailyTiffinEntity.setLongitude(location.getLongitude());

            dailyTiffinEntity.setTaste(true);
            dailyTiffinEntityList.add(dailyTiffinEntity);
        }

        return dailyTiffinEntityList;
    }
}