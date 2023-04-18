package fpt.sep490.service.impl;

import fpt.sep490.entity.Address;
import fpt.sep490.entity.Role;
import fpt.sep490.entity.User;
import fpt.sep490.exception.FoodifyAPIException;
import fpt.sep490.payload.AddressDto;
import fpt.sep490.payload.SignUpDto;
import fpt.sep490.payload.StringBoolObject;
import fpt.sep490.payload.UserDto;
import fpt.sep490.repository.AddressRepository;
import fpt.sep490.repository.RoleRepository;
import fpt.sep490.repository.UserRepository;
import fpt.sep490.service.AuthService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class AuthServiceImpl implements AuthService {
    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private AddressRepository addressRepository;
    private ModelMapper mapper;

    public AuthServiceImpl(AuthenticationManager authenticationManager, UserRepository userRepository, RoleRepository roleRepository, AddressRepository addressRepository, ModelMapper mapper) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.addressRepository = addressRepository;
        this.mapper = mapper;
    }

    @Override
    public StringBoolObject checkEmailOrPhoneNumberExist(SignUpDto signUpDto) {
        if(userRepository.existsByEmail(signUpDto.getEmail())){
            StringBoolObject object = new StringBoolObject();
            object.setTitle("emailExist");
            object.setIsTrue(true);
            return object;
        }
        else {
            if(userRepository.existsByPhoneNumber(signUpDto.getPhoneNumber())){
                StringBoolObject object = new StringBoolObject();
                object.setTitle("phoneNumExist");
                object.setIsTrue(true);
                return object;
            }
            else {
                StringBoolObject object = new StringBoolObject();
                object.setTitle("emailOrPhoneNumExist");
                object.setIsTrue(false);
                return object;
            }
        }
    }

    @Override
    public StringBoolObject checkIdentifiedCodeExist(String code) {
        if(userRepository.existsByIdentifiedCode(code)){
            return new StringBoolObject("existed", true);
        }
        return new StringBoolObject("existed", false);
    }

    @Override
    public UserDto register(SignUpDto signUpDto) {
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

        User savedUser = userRepository.save(user);
        savedUser.setAddresses(new HashSet<>());

        AddressDto addressDto = signUpDto.getAddressDto();

        if(addressRepository.existsByAddress(addressDto.getAddress())){
            Address address = addressRepository.findAddressByAddress(addressDto.getAddress())
                    .orElseThrow(() -> new FoodifyAPIException(HttpStatus.NOT_FOUND, "Address not found"));

            savedUser.getAddresses().add(address);
            savedUser.setDefaultAddress(address.getId());
            address.getUsers().add(savedUser);

            userRepository.save(savedUser);
            addressRepository.save(address);
        }
        else {
            Address address = mapper.map(addressDto, Address.class);
            Address newAddress = addressRepository.save(address);
            newAddress.setUsers(new HashSet<>());

            newAddress.getUsers().add(savedUser);
            savedUser.getAddresses().add(newAddress);
            savedUser.setDefaultAddress(newAddress.getId());

            addressRepository.save(newAddress);
            userRepository.save(savedUser);
        }
        return mapper.map(savedUser, UserDto.class);
    }
}
