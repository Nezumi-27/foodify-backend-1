package fpt.sep490.service;

import fpt.sep490.payload.*;

public interface AuthService {
//    String login(LoginDto loginDto);

    StringBoolObject checkEmailOrPhoneNumberExist(SignUpDto signUpDto);
    UserDto register(SignUpDto signUpDto);
}
