package fpt.sep490.controller;

import fpt.sep490.payload.*;
import fpt.sep490.service.UserService;
import fpt.sep490.utils.AppConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Api("CRUD APIs for User Resources")
@RestController
@RequestMapping("/api/users")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'SHOP')")
    @ApiOperation("Create new user")
    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody UserDto userDto){
        return new ResponseEntity<>(userService.createUser(userDto), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation("Get All Users")
    @GetMapping
    public UserResponsePageable getAllUsers(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_USER_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ){
        return userService.getAllUser(pageNo, pageSize, sortBy, sortDir);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER','SHOP','SHIPPER')")
    @ApiOperation("Get Users By Roles")
    @GetMapping("/roles/{roleName}")
    public UserResponsePageable getUsersByRole(
            @PathVariable(value = "roleName") String roleName,
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_USER_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ){
        return userService.getUsersByRoles(roleName, pageNo, pageSize, sortBy, sortDir);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER','SHOP','SHIPPER')")
    @ApiOperation("Find Users By Email Or Phone Number And Role")
    @GetMapping("/roles/{roleName}/email")
    public ResponseEntity<UserResponsePageable> getUsersByEmailOrPhoneNumberAndRole(
            @RequestParam(value = "emailOrPhoneNumber") String emailOrPhoneNumber,
            @PathVariable(value = "roleName") String roleName,
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_USER_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ){
        return ResponseEntity.ok(userService.getUserByEmailOrPhoneNumberAndRole(emailOrPhoneNumber ,roleName, pageNo, pageSize, sortBy, sortDir));
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER','SHOP','SHIPPER')")
    @ApiOperation("Find Users By Email Or Phone Number")
    @GetMapping("/email/search")
    public ResponseEntity<UserResponsePageable> getUsersByEmailOrPhoneNumberContaining(
            @RequestParam(value = "emailOrPhoneNumber") String emailOrPhoneNumber,
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_USER_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ){
        return ResponseEntity.ok(userService.getUsersByEmailOrPhoneNumber(emailOrPhoneNumber , pageNo, pageSize, sortBy, sortDir));
    }

    @PreAuthorize("hasRole('ADMIN') || hasAnyRole('SHOP', 'SHIPPER', 'USER') and principal.password==#userId.toString()")
    @ApiOperation("Get User By Id")
    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable(value = "userId") Long userId){
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @PreAuthorize("hasRole('ADMIN') || hasAnyRole('SHOP', 'SHIPPER', 'USER') and principal.username==#emailOrPhoneNumber")
    @ApiOperation("Get User By Email or Phone Number")
    @GetMapping("/email/{emailOrPhoneNumber}")
    public ResponseEntity<UserResponse> getUserByEmailOrPhoneNumber(@PathVariable(value = "emailOrPhoneNumber") String emailOrPhoneNumber){
        return ResponseEntity.ok(userService.getUserByEmailOrPhoneNumber(emailOrPhoneNumber));
    }
    
    @PreAuthorize("hasRole('ADMIN') || hasAnyRole('SHOP', 'SHIPPER', 'USER') and principal.username==#emailOrPhoneNumber")
    @ApiOperation("Get User By Email or Phone Number V1")
    @GetMapping("/v1/email")
    public ResponseEntity<UserResponse> getUserByEmailOrPhoneNumberV1(@RequestParam(value = "emailOrPhoneNumber") String emailOrPhoneNumber){
        return ResponseEntity.ok(userService.getUserByEmailOrPhoneNumber(emailOrPhoneNumber));
    }



    @PreAuthorize("hasRole('ADMIN') || hasRole('SHOP') || hasAnyRole('SHIPPER', 'USER') and principal.password==#userId.toString()")
    @ApiOperation("Update User by Id")
    @PutMapping("/{userId}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable(value = "userId") Long userId,
                                                   @RequestBody UserDto userDto){
        return ResponseEntity.ok(userService.updateUser(userId, userDto));
    }

    @PreAuthorize("hasRole('ADMIN') || hasAnyRole('SHOP', 'SHIPPER', 'USER') and principal.password==#userId.toString()")
    @ApiOperation("Delete user by Id")
    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable(value = "userId") Long userId){
        userService.deleteUser(userId);
        return ResponseEntity.ok("User deleted successfully");
    }

    @PreAuthorize("hasRole('ADMIN') || hasAnyRole('USER') and principal.password==#userId.toString()")
    @ApiOperation("Create love product")
    @PostMapping("/{userId}/loves/{productId}")
    public ResponseEntity<StringBoolObject> createLoveProduct(@PathVariable(value = "userId") Long userId,
                                                              @PathVariable(value = "productId") Long productId){
        return ResponseEntity.ok(userService.createLoveProduct(productId, userId));
    }

    @PreAuthorize("hasRole('ADMIN') || hasAnyRole('USER') and principal.password==#userId.toString()")
    @ApiOperation("Get All Love Product By User Id")
    @GetMapping("/{userId}/loves")
    public ProductResponsePageable getAllLoveProducts(@PathVariable(value = "userId") Long userId,
                                                      @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
                                                      @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PRODUCT_PAGE_SIZE, required = false) int pageSize,
                                                      @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
                                                      @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir){
        return userService.getLoveProductByUserId(userId, pageNo, pageSize, sortBy, sortDir);
    }

    @PreAuthorize("hasRole('ADMIN') || hasAnyRole('USER') and principal.password==#userId.toString()")
    @ApiOperation("Get Love Product By Id")
    @GetMapping("/{userId}/loves/{productId}")
    public StringBoolObject getLoveProduct(@PathVariable(value = "userId") Long userId,
                                           @PathVariable(value = "productId") Long productId){
        return userService.getLoveProductByUserAndProductId(userId, productId);
    }

    @PreAuthorize("hasRole('ADMIN') || hasAnyRole('USER') and principal.password==#userId.toString()")
    @ApiOperation("Delete love product")
    @DeleteMapping("/{userId}/loves/{productId}")
    public ResponseEntity<StringBoolObject> deleteLoveProduct(@PathVariable(value = "userId") Long userId,
                                                    @PathVariable(value = "productId") Long productId){
        return ResponseEntity.ok(userService.deleteLoveProduct(productId, userId));
    }

    @PreAuthorize("hasRole('ADMIN') || hasAnyRole('SHOP', 'SHIPPER', 'USER') and principal.password==#userId.toString()")
    @ApiOperation("Create Address for User")
    @PostMapping("/{userId}/addresses")
    public ResponseEntity<StringBoolObject> createAddressForUser(@PathVariable(value = "userId") Long userId,
                                                       @RequestBody AddressDto addressDto){
        return ResponseEntity.ok(userService.createAddressForUser(userId, addressDto));
    }

    @PreAuthorize("hasRole('ADMIN') || hasAnyRole('SHOP', 'SHIPPER', 'USER') and principal.password==#userId.toString()")
    @ApiOperation("Get Addresses by User")
    @GetMapping("/{userId}/addresses")
    public AddressResponsePageable getAddressByUser(@PathVariable(value = "userId") Long userId,
                                                    @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
                                                    @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PRODUCT_PAGE_SIZE, required = false) int pageSize,
                                                    @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
                                                    @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir){
        return userService.getAddressesByUser(userId, pageNo, pageSize, sortBy, sortDir);
    }

    @PreAuthorize("hasRole('ADMIN') || hasAnyRole('SHOP', 'SHIPPER', 'USER') and principal.password==#userId.toString()")
    @ApiOperation("Update user address")
    @PutMapping("/{userId}/addresses/{addressId}")
    public ResponseEntity<AddressDto> updateUserAddress(@PathVariable(value = "userId") Long userId,
                                                        @PathVariable(value = "addressId") Long addressId,
                                                        @RequestBody AddressDto addressDto){
        return ResponseEntity.ok(userService.updateUserAddress(userId, addressId, addressDto));
    }

    @PreAuthorize("hasRole('ADMIN') || hasAnyRole('SHOP', 'SHIPPER', 'USER') and principal.password==#userId.toString()")
    @ApiOperation("Update user default address")
    @PutMapping("/{userId}/addresses/default")
    public ResponseEntity<StringBoolObject> updateUserAddress(@PathVariable(value = "userId") Long userId,
                                                        @RequestParam(value = "addressId") Long addressId){
        return ResponseEntity.ok(userService.updateUserDefaultAddress(userId, addressId));
    }

    @PreAuthorize("hasRole('ADMIN') || hasAnyRole('SHOP', 'SHIPPER', 'USER') and principal.password==#userId.toString()")
    @ApiOperation("Delete user address")
    @DeleteMapping("/{userId}/addresses/{addressId}")
    public ResponseEntity<StringBoolObject> deleteUserAddress(@PathVariable(value = "userId") Long userId,
                                                    @PathVariable(value = "addressId") Long addressId){
        return ResponseEntity.ok(userService.deleteUserAddress(userId, addressId));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation("Count new users by day")
    @GetMapping("/count")
    public ResponseEntity<Integer> count(@RequestParam(value = "day") int day){
        return ResponseEntity.ok(userService.countUserRegisterByDay(day));
    }

    @ApiOperation("Update FCM Token")
    @PutMapping("/{userId}/update/fcm")
    public ResponseEntity<StringBoolObject> updateUserFcmToken(
            @PathVariable(value = "userId") Long userId,
            @RequestBody String token){
        return ResponseEntity.ok(userService.updateFcmToken(userId, token));
    }
}
