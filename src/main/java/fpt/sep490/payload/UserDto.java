package fpt.sep490.payload;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import java.sql.Timestamp;
import java.util.Date;

@Data
public class UserDto {
    private Long id;
    private String email;
    private String password;
    private String firstname;
    private String lastName;
    private Date dateOfBirth;
    private String phoneNumber;
    private String imageUrl;
    private boolean isLocked;
    private Timestamp createdTime;
    private String identifiedCode;
}
