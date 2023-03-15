package fpt.sep490.service;

import fpt.sep490.entity.Address;
import fpt.sep490.payload.AddressDto;
import fpt.sep490.payload.LoginDto;
import fpt.sep490.payload.SignUpDto;

public interface AuthService {
//    String login(LoginDto loginDto);

    String register(SignUpDto signUpDto);
}
