package fpt.sep490.service;

import fpt.sep490.payload.*;

public interface AuthService {
//    String login(LoginDto loginDto);

    StringBoolObject checkEmailOrPhoneNumberExist(SignUpDto signUpDto);

    StringBoolObject checkIdentifiedCodeExist(String code);
    UserDto register(SignUpDto signUpDto);
}
