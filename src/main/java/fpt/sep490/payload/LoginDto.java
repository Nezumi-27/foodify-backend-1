package fpt.sep490.payload;

import lombok.Data;

@Data
public class LoginDto {
    private String emailOrPhoneNumber;
    private String password;
}
