package fpt.sep490.controller;

import fpt.sep490.payload.*;
import fpt.sep490.service.AuthService;
import fpt.sep490.service.ShopService;
import fpt.sep490.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(value = "Login and Signup Controller")
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private AuthService authService;
    private UserService userService;

    private ShopService shopService;

    public AuthController(AuthService authService, UserService userService, ShopService shopService) {
        this.authService = authService;
        this.userService = userService;
        this.shopService = shopService;
    }

    @ApiOperation("Check email or phone number")
    @PostMapping("/check")
    public ResponseEntity<StringBoolObject> checkEmailOrPhoneNumber(@RequestBody SignUpDto signUpDto){
        return ResponseEntity.ok(authService.checkEmailOrPhoneNumberExist(signUpDto));
    }

    @ApiOperation("Check email in DB")
    @GetMapping("/check-email")
    public ResponseEntity<StringBoolObject> checkEmailExist(@RequestParam(value = "email") String email){
        return ResponseEntity.ok(authService.checkEmailExist(email));
    }

    @ApiOperation("Check identified code")
    @GetMapping("/check")
    public ResponseEntity<StringBoolObject> checkIdentifiedCodeExist(@RequestParam("code") String code){
        return ResponseEntity.ok(authService.checkIdentifiedCodeExist(code));
    }

    @ApiOperation("Sign-up mobile user")
    @PostMapping("/signup")
    public ResponseEntity<UserDto> registerUser(@RequestBody SignUpDto signUpDto){
        return ResponseEntity.ok(authService.register(signUpDto));
    }

    @ApiOperation("Register new web user")
    @PostMapping("/register")
    public ResponseEntity<UserResponse> createUser(@RequestBody UserDto userDto){
        return new ResponseEntity<>(userService.createUser(userDto), HttpStatus.CREATED);
    }

    @ApiOperation("Sign-up address for new User")
    @PostMapping("/{userId}/addresses")
    public ResponseEntity<StringBoolObject> createAddressForUser(@PathVariable(value = "userId") Long userId,
                                                                 @RequestBody AddressDto addressDto){
        return ResponseEntity.ok(userService.createAddressForUser(userId, addressDto));
    }

    @ApiOperation("Register Shop")
    @PostMapping("/register-shop")
    public ResponseEntity<ShopDto> createShop(@RequestBody ShopDto shopDto){
        return new ResponseEntity<>(shopService.createShop(shopDto), HttpStatus.CREATED);
    }

}
