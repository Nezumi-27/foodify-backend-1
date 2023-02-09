package fpt.sep490.payload;

import fpt.sep490.entity.Role;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.sql.Timestamp;
import java.util.Date;

@Data
public class SignUpDto {
    private String email;
    private String phoneNumber;
    private String password;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String imageUrl;
    private Boolean isLocked;
    private String identifiedCode;
    private String roleName;
}
