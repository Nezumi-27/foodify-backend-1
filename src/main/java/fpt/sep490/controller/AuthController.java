package fpt.sep490.controller;

import fpt.sep490.entity.Role;
import fpt.sep490.entity.User;
import fpt.sep490.payload.SignUpDto;
import fpt.sep490.repository.RoleRepository;
import fpt.sep490.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public AuthController(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignUpDto signUpDto){
        User user = new User();
        user.setEmail(signUpDto.getEmail());
        user.setPhoneNumber(signUpDto.getPhoneNumber());
        user.setPassword(signUpDto.getPassword());
        user.setFirstname(signUpDto.getFirstName());
        user.setLastName(signUpDto.getLastName());
        user.setDateOfBirth(signUpDto.getDateOfBirth());
        user.setImageUrl(signUpDto.getImageUrl());
        user.setIdentifiedCode(signUpDto.getIdentifiedCode());
        System.out.println(signUpDto.getIsLocked());
        user.setLocked(signUpDto.getIsLocked());

        Role role = roleRepository.findByName(signUpDto.getRoleName());
        user.setRole(role);

        userRepository.save(user);
        return new ResponseEntity<>("User registered successfully!", HttpStatus.OK);
    }
}
