package fpt.sep490.service.impl;

import fpt.sep490.entity.Role;
import fpt.sep490.entity.User;
import fpt.sep490.exception.FoodifyAPIException;
import fpt.sep490.payload.LoginDto;
import fpt.sep490.payload.SignUpDto;
import fpt.sep490.repository.RoleRepository;
import fpt.sep490.repository.UserRepository;
import fpt.sep490.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    public AuthServiceImpl(AuthenticationManager authenticationManager, UserRepository userRepository, RoleRepository roleRepository) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public String register(SignUpDto signUpDto) {
        if(userRepository.existsByEmail(signUpDto.getEmail())){
            throw new FoodifyAPIException(HttpStatus.BAD_REQUEST, "This email has been taken");
        }

        if(userRepository.existsByPhoneNumber(signUpDto.getPhoneNumber())){
            throw new FoodifyAPIException(HttpStatus.BAD_REQUEST, "This phone number has been taken");
        }

        User user = new User();
        user.setEmail(signUpDto.getEmail());
        user.setPhoneNumber(signUpDto.getPhoneNumber());
        user.setFullName(signUpDto.getFullName());
        user.setDateOfBirth(signUpDto.getDateOfBirth());
        user.setImageUrl(signUpDto.getImageUrl());
        user.setIdentifiedCode(signUpDto.getIdentifiedCode());
        user.setIsLocked(signUpDto.getIsLocked());

        Role role = roleRepository.findByName(signUpDto.getRoleName())
                .orElseThrow(() -> new FoodifyAPIException(HttpStatus.NOT_FOUND, "Role doesn't found with name: " + signUpDto.getRoleName()));
        user.setRole(role);

        userRepository.save(user);
        return "User register successfully";
    }
}
