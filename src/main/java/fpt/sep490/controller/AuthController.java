package fpt.sep490.controller;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import fpt.sep490.entity.User;
import fpt.sep490.payload.*;
import fpt.sep490.repository.UserRepository;
import fpt.sep490.service.AuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Api(value = "Login and Signup Controller")
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @ApiOperation("Check email or phone number")
    @PostMapping("/check")
    public ResponseEntity<StringBoolObject> checkEmailOrPhoneNumber(@RequestBody SignUpDto signUpDto){
        return ResponseEntity.ok(authService.checkEmailOrPhoneNumberExist(signUpDto));
    }

    @ApiOperation("Sign-up")
    @PostMapping("/signup")
    public ResponseEntity<UserDto> registerUser(@RequestBody SignUpDto signUpDto){
        return ResponseEntity.ok(authService.register(signUpDto));
    }

}