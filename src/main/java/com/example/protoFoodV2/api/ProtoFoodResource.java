package com.example.protoFoodV2.api;

import com.example.protoFoodV2.databaseModels.*;
import com.example.protoFoodV2.exceptions.EntityNotFoundException;
import com.example.protoFoodV2.exceptions.RenderableException;
import com.example.protoFoodV2.exceptions.RenderableExceptionGenerator;
import com.example.protoFoodV2.service.*;
import com.example.protoFoodV2.utils.UserAction;
import com.example.protoFoodV2.utils.Util;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

import javax.management.openmbean.KeyAlreadyExistsException;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.*;
import java.util.concurrent.ExecutionException;

@Slf4j
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
    public ResponseEntity<List<UserEntity>> listAllUsers() {
        log.info("Attempting to list all users");
        try {
            List<UserEntity> userEntityList = userManagementService.listAllUsers();

            return ResponseEntity.ok(userEntityList);
        } catch (Exception e) {
            log.error("Exception occurred while listing all users, Exception : {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//            throw RenderableExceptionGenerator.generateInternalServerErrorException();
        }
    }

    // USER APIs
    @PostMapping("/createUser")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void createUser(@RequestBody UserEntity userEntity) {
        log.info("Attempting to create a new userEntity : {}", userEntity.toString());
        /*
        1. Check for appropriate AuthN/Z
        2. Validate passed parameters
        3. Check if user already exists
        4. If none, create user and return
         */
        try {
            userManagementService.getUserByPhoneNumber(userEntity.getContact());
            log.error("User with contact {} already exists. Cannot proceed.", userEntity.getContact());
            throw RenderableExceptionGenerator.generateInternalServerErrorException("Entry already exists");
        } catch (EntityNotFoundException ene) {
            log.info("Adding new user");
        } catch (Exception e) {
            log.error("Unexpected exception occurred : {}, {}", e.getClass(), e.getMessage());
            throw RenderableExceptionGenerator.generateInternalServerErrorException(e.getMessage());
        }

        try {
            userManagementService.createUser(userEntity);
        } catch (Exception e) {
            log.error("Exception occurred while creating user. " +
                    "Exception : {}, {}", e.getClass(), e.getMessage());
            throw RenderableExceptionGenerator.generateInternalServerErrorException(e.getMessage(), e);
        }
    }

    public void updateUser() {
    }

    public void deleteUser() {
    }

    @GetMapping("/getUser")
    @ResponseStatus(code = HttpStatus.OK)
    public UserEntity getUser(@RequestParam String userPhoneNumber) {
        log.info("Attempting to fetch userInfo with phoneNumber {}", userPhoneNumber);
        try {
            UserEntity user = userManagementService.getUserByPhoneNumber(userPhoneNumber);
            return user;
        } catch (EntityNotFoundException e) {
            log.error("Could not find user with phoneNumber {}", userPhoneNumber);
            throw RenderableExceptionGenerator.generateInternalServerErrorException(e.getMessage(), e);
        } catch (Exception e) {
            log.error("Unexpected exception occurred : {}, {}", e.getClass(), e.getMessage());
            throw RenderableExceptionGenerator.generateInternalServerErrorException(e.getMessage());
        }
    }

    // SUBSCRIPTION APIs
    @PostMapping("/addSubscription") // This is an ADMIN API
    @ResponseStatus(code = HttpStatus.CREATED)
    public void postNewSubscription(@RequestBody SubscriptionEntity subscriptionEntity) {
        log.info("Attempting to register new subscription {}", subscriptionEntity.toString());
        /*
        1. Check for appropriate AuthN/Z
        2. Validate passed parameters
        3. create subscription and return
         */
        try {
            subscriptionManagementService.addNewSubscription(subscriptionEntity);
        } catch (Exception e) {
            log.error("Exception occurred while creating subscription. {}, {}", e.getClass(), e.getMessage());
            throw RenderableExceptionGenerator.generateInternalServerErrorException(e.getMessage(), e);
        }
    }

    @GetMapping("/viewSubscription") // This is an ADMIN API
    @ResponseStatus(code = HttpStatus.OK)
    public SubscriptionEntity viewSubscriptionRecord(
            @RequestParam(name = "id") String subscriptionId) {
        log.info("Attempting to fetch subscription record for Id : {}", subscriptionId);
        /*
        1. Validate parameters
        2. Validate AuthN/Z
        3. Fetch subscription record and return
         */
        try {
            return subscriptionManagementService.findSubscriptionById(subscriptionId);
        } catch (EntityNotFoundException e) {
            log.error("Exception occurred white fetching subscription : {}, {}, {}", subscriptionId, e.getClass(), e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("Unexpected exception occurred : {}, {}", e.getClass(), e.getMessage());
            throw RenderableExceptionGenerator.generateInternalServerErrorException(e.getMessage());
        }
    }

    public void chooseSubscription() {
    }

    @GetMapping("/listAllSubscriptions") // This is an ADMIN API
    @ResponseStatus(code = HttpStatus.OK)
    public List<SubscriptionEntity> listAllSubscriptions() {
        log.info("Attempting to list all subscriptions");
        try {
            return subscriptionManagementService.listAllSubscriptionRecords();
        } catch (Exception e) {
            log.error("Failed to list all subscriptions. Exception Occurred. {}, {}", e.getClass(), e.getMessage());
            throw RenderableExceptionGenerator.generateInternalServerErrorException();
        }
    }

    @GetMapping("/listActiveSubscriptions")
    @ResponseStatus(code = HttpStatus.OK)
    public List<SubscriptionEntity> listActiveSubscriptions() {
        log.info("Attempting to list all active subscriptions");

        try {
            return subscriptionManagementService.listActiveSubscriptionRecords();
        } catch (Exception e) {
            log.error("Failed to list all active subscriptions. Exception: {}, {}", e.getClass(), e.getMessage());
            throw RenderableExceptionGenerator.generateInternalServerErrorException();
        }
    }

    // EXTRA-TIFFIN APIs
    @PostMapping("/addExtraTiffinRecord")
    @ResponseStatus(code = HttpStatus.OK)
    public void addExtraTiffinRecord(@RequestBody ExtraEntity extraTiffinEntity) {
        log.info("Attempting to add new extra tiffin record : {}", extraTiffinEntity.toString());
        /*
        1. Validate params
        2. Validate AuthN/Z
        3. add extra tiffin info and return
        */
        try {
            extraTiffinManagementService.addExtraTiffinRecord(extraTiffinEntity);
        } catch (Exception e) {
            log.error("Error adding extraTiffin record {}. Exception: {}, {}", extraTiffinEntity.toString(), e.getClass(), e.getMessage());
            throw RenderableExceptionGenerator.generateInternalServerErrorException();
        }
    } // include to-from statements here

    public void viewExtraTiffinHistory() {
    }

    // SKIP-TIFFIN APIs
    @PostMapping("/addSkipTiffinRecord")
    @ResponseStatus(code = HttpStatus.OK)
    public void addSkipTiffinRecord(@RequestBody SkipEntity skipModel) {
        log.info("Attempting to add skip tiffin record : {}", skipModel.toString());
        try {
            skipTiffinManagementService.addSkipTiffinRecord(skipModel);
        } catch (Exception e) {
            log.error("Error adding skip tiffin record {}. Exception: {}, {}", skipModel.toString(), e.getClass(), e.getMessage());
            throw RenderableExceptionGenerator.generateInternalServerErrorException();
        }
    }

    public void viewSkipTiffinHistory() {
    }

    // LOCATION APIs
    @PostMapping("/addLocation")
    @ResponseStatus(code = HttpStatus.OK)
    public void postNewLocation(@RequestBody LocationEntity locationEntity) {
        log.info("Attempting to add new location: {}", locationEntity.toString());
        try {
            locationManagementService.addNewLocation(locationEntity);
        } catch (Exception e) {
            log.error("Error adding location: {}, Exception {}, {}", locationEntity.toString(), e.getClass(), e.getMessage());
            throw RenderableExceptionGenerator.generateInternalServerErrorException();
        }
    }

    @GetMapping("/fetchUserAllLocations/{userPhoneNumber}")
    @ResponseStatus(code = HttpStatus.OK)
    public List<LocationEntity> fetchUserAllLocations(@PathVariable String userPhoneNumber) {
        log.info("Attempting to fetch user->{} all locations", userPhoneNumber);
        String parsedPhoneNumber;

        try {
            parsedPhoneNumber = Util.refactorPhoneNumber(userPhoneNumber);
        } catch (InvalidParameterException ipe) {
            log.error("Invalid phone number. Exception: {}", ipe.getMessage());
            throw ipe;
        }

        try {
            return locationManagementService.fetchUserAllLocations(parsedPhoneNumber);
        } catch (Exception e) {
            log.error("Failed to fetch user->{} all locations. Exception: {}, {}", parsedPhoneNumber, e.getClass(), e.getMessage());
            throw RenderableExceptionGenerator.generateInternalServerErrorException();
        }
    }

    @GetMapping("/getLocation/{locationId}")
    @ResponseStatus(code = HttpStatus.OK)
    public LocationEntity getLocation(@PathVariable String locationId) {
        log.info("Attempting to fetch location with Id : {}", locationId);

        try {
            return locationManagementService.getLocationById(locationId);
        } catch (EntityNotFoundException ene) {
            log.error("Exception occurred white fetching location : {}, {}, {}", locationId, ene.getClass(), ene.getMessage());
            throw ene;
        } catch (Exception e) {
            log.error("Unexpected exception occurred : {}, {}", e.getClass(), e.getMessage());
            throw RenderableExceptionGenerator.generateInternalServerErrorException(e.getMessage());
        }
    }

    public void deleteLocation() {
    }

    public void updateLocation() {
    }

    @GetMapping("/fetchUserClosestLocation")
    @ResponseStatus(code = HttpStatus.OK)
    public LocationEntity fetchUserClosestLocation(
            @RequestParam(name = "latitude") double latitude,
            @RequestParam(name = "longitude") double longitude,
            @RequestParam(name = "userPhoneNumber") String userPhoneNumber) {
        log.info("Attempting to fetch user->{} closest location", userPhoneNumber);
        String parsedPhoneNumber;

        /*
        1. Validate all parameters
        2. Validate AuthN/Z
        3. Fetch the closest location and return
         */
        try {
            parsedPhoneNumber = Util.refactorPhoneNumber(userPhoneNumber);
        } catch (InvalidParameterException ipe) {
            log.error("Invalid phone number. Exception: {}", ipe.getMessage());
            throw ipe;
        }

        try {
            return locationManagementService.fetchClosestLocation(latitude, longitude, parsedPhoneNumber);
        } catch (Exception e) {
            log.error("Failed to fetch user->{} closest location. Exception: {}, {}", parsedPhoneNumber, e.getClass(), e.getMessage());
            throw RenderableExceptionGenerator.generateInternalServerErrorException();
        }
    }

    // PAYMENT APIs
    @PostMapping("/recordNewPayment")
    @ResponseStatus(code = HttpStatus.OK)
    public void recordNewPayment(@RequestBody PaymentEntity paymentEntity) {
        log.info("Attempting to insert new payment record: {}", paymentEntity.toString());
        try {
            paymentManagementService.recordNewPayment(paymentEntity);
        } catch (Exception e) {
            log.error("Error inserting new payment record. Exception: {}, {}", e.getClass(), e.getMessage());
            throw RenderableExceptionGenerator.generateInternalServerErrorException();
        }
    }
    public void viewPaymentHistory() {
    }

    // TASTE APIs
    @PostMapping("/addTasteTiffinRecord")
    @ResponseStatus(code = HttpStatus.OK)
    public void addTasteRecord(@RequestBody TasteEntity tasteEntity) {
        log.info("Attempting to add taste record: {}", tasteEntity.toString());
        try {
            tasteManagementService.addNewTasteRecord(tasteEntity);
        } catch (Exception e) {
            log.error("Error adding taste record. Exception: {}, {}", e.getClass(), e.getMessage());
            throw RenderableExceptionGenerator.generateInternalServerErrorException();
        }
    }

    //TIFFIN APIs
    @PostMapping("/addTiffinRecord")
    @ResponseStatus(code = HttpStatus.OK)
    public void addTiffinRecord(@RequestBody TiffinEntity tiffinModel) {
        log.info("Attempting to add new tiffin record: {}", tiffinModel.toString());
        try {
            tiffinManagementService.addNewTiffinRecord(tiffinModel);
        } catch (Exception e) {
            log.error("Error adding tiffin record. Exception: {}, {}", e.getClass(), e.getMessage());
            throw RenderableExceptionGenerator.generateInternalServerErrorException();
        }
    }

    @GetMapping("/getUserActiveTiffin")
    @ResponseStatus(code = HttpStatus.OK)
    public TiffinEntity fetchUserActiveTiffin(
            @RequestParam String userPhoneNumber,
            @RequestParam String dateTime) {
        log.info("Attempting to fetch user->{} active tiffins for {}", userPhoneNumber, dateTime);
        String parsedPhoneNumber;

        try {
            parsedPhoneNumber = Util.refactorPhoneNumber(userPhoneNumber);
        } catch (InvalidParameterException ipe) {
            log.error("Invalid phone number. Exception: {}", ipe.getMessage());
            throw ipe;
        }

        try {
            return tiffinManagementService.fetchUserActiveTiffin(parsedPhoneNumber, dateTime);
        } catch (Exception e) {
            log.error("Failed to fetch user->{} active tiffins for {}. Exception: {}, {}", userPhoneNumber, dateTime, e.getClass(), e.getMessage());
            throw RenderableExceptionGenerator.generateInternalServerErrorException();
        }
    }

    @GetMapping("/getUserFutureTiffin")
    @ResponseStatus(code = HttpStatus.OK)
    public TiffinEntity fetchUserFutureTiffin(
            @RequestParam String userPhoneNumber,
            @RequestParam String dateTime) {
        log.info("Attempting to fetch user->{} future tiffins for {}", userPhoneNumber, dateTime);
        String parsedPhoneNumber;

        try {
            parsedPhoneNumber = Util.refactorPhoneNumber(userPhoneNumber);
        } catch (InvalidParameterException ipe) {
            log.error("Invalid phone number. Exception: {}", ipe.getMessage());
            throw ipe;
        }

        try {
            return tiffinManagementService.fetchUserFutureTiffin(parsedPhoneNumber, dateTime);
        } catch (Exception e) {
            log.error("Failed to fetch user->{} future tiffins for {}. Exception: {}, {}", userPhoneNumber, dateTime, e.getClass(), e.getMessage());
            throw RenderableExceptionGenerator.generateInternalServerErrorException();
        }
    }

    @GetMapping("/getTiffinInfo")
    public TiffinEntity getTiffinInfo(@RequestParam String tiffinId) {
        log.info("Attempting to get tiffin by Id : {}", tiffinId);

        try {
            return tiffinManagementService.getTiffinInfo(tiffinId);
        } catch (EntityNotFoundException ene) {
            log.error("Failed to get tiffin by Id : {}. Exception: {}, {}", tiffinId, ene.getClass(), ene.getMessage());
            throw ene;
        } catch (Exception e) {
            log.error("Unexpected exception occurred : {}, {}", e.getClass(), e.getMessage());
            throw RenderableExceptionGenerator.generateInternalServerErrorException(e.getMessage());
        }
    }

    public void viewTiffinHistory() {
    }

    // ORDER APIs
    @PostMapping("/addNewOrderRecord")
    @ResponseStatus(code = HttpStatus.OK)
    public void addNewOrderRecord(@RequestBody OrderEntity order) {
        log.info("Attempting to add new order: {}", order.toString());
        try {
            orderManagementService.addNewOrderRecord(order);
        } catch (Exception e) {
            log.error("Error inserting new order: {}. Exception: {}, {}", order.toString(), e.getClass(), e.getMessage());
            throw RenderableExceptionGenerator.generateInternalServerErrorException();
        }
    }

    @GetMapping("/getUserAllOrders/{userPhoneNumber}")
    @ResponseStatus(code = HttpStatus.OK)
    public List<ConsolidatedOrder> getUserAllConsolidatedOrders(
            @PathVariable String userPhoneNumber,
            @RequestParam(defaultValue = "0") int pageNumber) {
        String parsedPhoneNumber;

        try {
            parsedPhoneNumber = Util.refactorPhoneNumber(userPhoneNumber);
        } catch (InvalidParameterException ipe) {
            log.error("Invalid phone number. Exception: {}", ipe.getMessage());
            throw ipe;
        }

        PageRequest pageRequest = PageRequest.of(pageNumber, PAGE_SIZE, Sort.by("timeCreated").descending());
        Page<OrderEntity> paginatedOrders = orderManagementService.getUserAllOrders(parsedPhoneNumber, pageRequest);
        List<OrderEntity> orderList = paginatedOrders.getContent();

        List<ConsolidatedOrder> consolidatedOrdersList = new ArrayList<>();
        for (OrderEntity order : orderList) {
            ConsolidatedOrder consolidatedOrder = new ConsolidatedOrder();
            consolidatedOrder.setOrderId(order.getOrderId());
            consolidatedOrder.setTimeCreated(order.getTimeCreated());

            try {
                TiffinEntity tiffin = tiffinManagementService.getTiffinInfo(order.getOrderId());
                consolidatedOrder.setTiffin(tiffin);
                consolidatedOrder.setAction(UserAction.Tiffin.name());
            } catch (Exception e) {
                // write a log message
            }

            try {
                TasteEntity taste = tasteManagementService.getTasteInfo(order.getOrderId());
                consolidatedOrder.setTaste(taste);
                consolidatedOrder.setAction(UserAction.Taste.name());
            } catch (Exception e) {
                // write a log message
            }

            try {
                ExtraEntity extra = extraTiffinManagementService.getExtraTiffinInfo(order.getOrderId());
                consolidatedOrder.setExtra(extra);
                consolidatedOrder.setAction(UserAction.ExtraTiffin.name());
            } catch (Exception e) {
                // write a log message
            }

            try {
                SkipEntity skip = skipTiffinManagementService.getSkipTiffinInfo(order.getOrderId());
                consolidatedOrder.setSkip(skip);
                consolidatedOrder.setAction(UserAction.SkipTiffin.name());
            } catch (Exception e) {
                // write a log message
            }

            try {
                PaymentEntity payment = paymentManagementService.getPaymentInfo(order.getOrderId());
                consolidatedOrder.setPayment(payment);
            } catch (Exception e) {
                // write a log message
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
        log.info("Attempting to fetch all tiffin for date: {} and meal: {}", date, meal);
        try {
            return tiffinManagementService.getAllTiffinForDate(date, meal);
        } catch (Exception e) {
            log.error("Exception occurred while fetching tiffins data. Exception : {}, {}", e.getClass(), e.getMessage());
            throw RenderableExceptionGenerator.generateInternalServerErrorException();
        }
    }

    @GetMapping("/fetchAllExtras")
    @ResponseStatus(code = HttpStatus.OK)
    public List<ExtraEntity> fetchAllExtras(
            @NonNull @RequestParam String date,
            @NonNull @RequestParam String meal) {
        log.info("Attempting to fetch all extra tiffins for date : {} and meal : {}", date, meal);
        try {
            return extraTiffinManagementService.getAllExtrasForDate(date, meal);
        } catch (Exception e) {
            log.error("Failed to fetch all extra tiffins data. Exception : {}, {}", e.getClass(), e.getMessage());
            throw RenderableExceptionGenerator.generateInternalServerErrorException();
        }
    }

    @GetMapping("/fetchAllSkips")
    @ResponseStatus(code = HttpStatus.OK)
    public List<SkipEntity> fetchAllSkips(
            @NonNull @RequestParam String date,
            @NonNull @RequestParam String meal) {
        log.info("Attempting to fetch all skip tiffins for date : {} and meal : {}", date, meal);
        try {
            return skipTiffinManagementService.getAllSkipsForDate(date, meal);
        } catch (Exception e) {
            log.error("Failed to fetch all skip tiffins data. Exception : {}, {}", e.getClass(), e.getMessage());
            throw RenderableExceptionGenerator.generateInternalServerErrorException();
        }
    }

    @GetMapping("/fetchAllTastes")
    @ResponseStatus(code = HttpStatus.OK)
    public List<TasteEntity> fetchAllTastes(
            @NonNull @RequestParam String date,
            @NonNull @RequestParam String meal) {
        log.info("Attempting to fetch all taste tiffin entries for date: {} and meal: {}", date, meal);
        try {
            return tasteManagementService.getAllTastesForDate(date, meal);
        } catch (Exception e) {
            log.error("Failed to fetch all taste data entries. Exception : {}, {}", e.getClass(), e.getMessage());
            throw RenderableExceptionGenerator.generateInternalServerErrorException();
        }
    }

    @GetMapping("/generateDailyTiffinReport")
    @ResponseStatus(code = HttpStatus.OK)
    public List<DailyTiffinEntity> generateDailyTiffinReport(
            @NonNull @RequestParam String date,
            @NonNull @RequestParam String meal) {
        log.info("Attempting to generate Daily Tiffin Report for date: {} and meal: {}", date, meal);
        List<TiffinEntity> tiffinForDate;
        List<ExtraEntity> extrasForDate;
        List<SkipEntity> skipsForDate;
        List<TasteEntity> tastesForDate;

        try {
            tiffinForDate = fetchAllTiffin(date, meal);
            extrasForDate = fetchAllExtras(date, meal);
            skipsForDate = fetchAllSkips(date, meal);
            tastesForDate = fetchAllTastes(date, meal);
        } catch (RenderableException re) {
            log.error("RenderableException reported : {}, {}", re.getClass(), re.getMessage());
            throw re;
        } catch (Exception e) {
            log.error("Failed to generate Daily Tiffin Report. Exception : {}, {}", e.getClass(), e.getMessage());
            throw RenderableExceptionGenerator.generateInternalServerErrorException();
        }

        List<DailyTiffinEntity> dailyTiffinEntityList = new ArrayList<>();

        try {
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

                UserEntity user = getUser(tiffin.getUserId());
                dailyTiffinEntity.setUserName(user.getFirstName() + " " + user.getLastName());

                LocationEntity location = getLocation(tiffin.getLocationId());
                dailyTiffinEntity.setAddress(Util.generateAddressFromLocationEntity(location));
                dailyTiffinEntity.setLatitude(location.getLatitude());
                dailyTiffinEntity.setLongitude(location.getLongitude());

                dailyTiffinEntity.setTaste(false);
                dailyTiffinEntityList.add(dailyTiffinEntity);
            }
            for (TasteEntity taste : tastesForDate) {
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
        } catch (Exception e) {
            log.error("Exception Reported for generateDailyTiffinReport. {}, {}", e.getClass(), e.getMessage());
            throw RenderableExceptionGenerator.generateInternalServerErrorException();
        }

        return dailyTiffinEntityList;
    }
}