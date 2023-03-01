package fpt.sep490.controller;

import fpt.sep490.payload.*;
import fpt.sep490.service.UserService;
import fpt.sep490.utils.AppConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api("CRUD APIs for User Resources")
@RestController
@RequestMapping("/api/users")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation("Create new user")
    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody UserDto userDto){
        return new ResponseEntity<>(userService.createUser(userDto), HttpStatus.CREATED);
    }

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

    @ApiOperation("Get User By Id")
    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable(value = "userId") Long userId){
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @ApiOperation("Update User by Id")
    @PutMapping("/{userId}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable(value = "userId") Long userId,
                                                   @RequestBody UserDto userDto){
        return ResponseEntity.ok(userService.updateUser(userId, userDto));
    }

    @ApiOperation("Delete user by Id")
    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable(value = "userId") Long userId){
        userService.deleteUser(userId);
        return ResponseEntity.ok("User deleted successfully");
    }

    @ApiOperation("Create love product")
    @PostMapping("/{userId}/loves/{productId}")
    public ResponseEntity<String> createLoveProduct(@PathVariable(value = "userId") Long userId,
                                                    @PathVariable(value = "productId") Long productId){
        userService.createLoveProduct(productId, userId);
        return ResponseEntity.ok("Create love product successfully");
    }

    @ApiOperation("Get All Love Product By User Id")
    @PostMapping("/{userId}/loves")
    public ProductResponsePageable getAllLoveProducts(@PathVariable(value = "userId") Long userId,
                                                      @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
                                                      @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PRODUCT_PAGE_SIZE, required = false) int pageSize,
                                                      @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
                                                      @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir){
        return userService.getLoveProductByUserId(userId, pageNo, pageSize, sortBy, sortDir);
    }

    @ApiOperation("Delete love product")
    @DeleteMapping("/{userId}/loves/{productId}")
    public ResponseEntity<String> deleteLoveProduct(@PathVariable(value = "userId") Long userId,
                                                    @PathVariable(value = "productId") Long productId){
        userService.deleteLoveProduct(productId, userId);
        return ResponseEntity.ok("Delete love product successfully");
    }

    @ApiOperation("Create Address for User")
    @PostMapping("/{userId}/addresses")
    public ResponseEntity<String> createAddressForUser(@PathVariable(value = "userId") Long userId,
                                                       @RequestBody AddressDto addressDto){
        userService.createAddressForUser(userId, addressDto);
        return ResponseEntity.ok("Address add to user successfully");
    }

    @ApiOperation("Get Addresses by User")
    @GetMapping("/{userId}/addresses")
    public AddressResponse getAddressByUser(@PathVariable(value = "userId") Long userId,
                                            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
                                            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PRODUCT_PAGE_SIZE, required = false) int pageSize,
                                            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
                                            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir){
        return userService.getAddressesByUser(userId, pageNo, pageSize, sortBy, sortDir);
    }

    @ApiOperation("Delete user address")
    @DeleteMapping("/{userId}/addresses/{addressId}")
    public ResponseEntity<String> deleteUserAddress(@PathVariable(value = "userId") Long userId,
                                                    @PathVariable(value = "addressId") Long addressId){
        userService.deleteUserAddress(userId, addressId);
        return ResponseEntity.ok("User Address delete successfully");
    }
}
