package fpt.sep490.payload;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    @NotEmpty
    @Email(message = "Email must be a valid email")
    private String email;

    @NotEmpty
    @Size(min = 10, message = "This must be a valid phone number")
    private String phoneNumber;

    @NotEmpty(message = "Full name must not be empty")
    private String fullName;

    @NotEmpty(message = "Date of birth must not be empty")
    private String dateOfBirth;

    @NotEmpty(message = "Image url must not be empty")
    private String imageUrl;

    @NotEmpty(message = "IsLocked must not be empty")
    private Boolean isLocked;

    @NotEmpty(message = "Identified code must not be empty")
    @Size(min = 9, message = "This must be a valid identified code")
    private String identifiedCode;

    @NotEmpty(message = "Role name must not be empty")
    private String roleName;
}
