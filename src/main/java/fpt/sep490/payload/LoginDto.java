package fpt.sep490.payload;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class LoginDto {
    @NotEmpty(message = "Email or Phone number must not be empty")
    private String emailOrPhoneNumber;

    @NotEmpty(message = "Password must not be empty")
    private String password;
}
