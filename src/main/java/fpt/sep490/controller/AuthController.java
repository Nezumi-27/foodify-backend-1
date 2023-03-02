package fpt.sep490.controller;

import fpt.sep490.entity.Role;
import fpt.sep490.entity.User;
import fpt.sep490.exception.FoodifyAPIException;
import fpt.sep490.payload.LoginDto;
import fpt.sep490.payload.SignUpDto;
import fpt.sep490.repository.RoleRepository;
import fpt.sep490.repository.UserRepository;
import fpt.sep490.service.AuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "Login and Signup Controller")
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @ApiOperation("Login")
    @PostMapping(value = {"/login", "/signin"})
    public ResponseEntity<String> login(@RequestBody  LoginDto loginDto){
        return ResponseEntity.ok(authService.login(loginDto));
    }

    @ApiOperation("Sign-up")
    @PostMapping("/signup")
    public ResponseEntity<String> registerUser(@RequestBody SignUpDto signUpDto){
        return ResponseEntity.ok(authService.register(signUpDto));
    }
}
