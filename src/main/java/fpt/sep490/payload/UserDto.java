package fpt.sep490.payload;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import java.sql.Timestamp;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String email;
    private String password;
    private String fullName;
    private String dateOfBirth;
    private String phoneNumber;
    private String imageUrl;
    private boolean isLocked;
    private Timestamp createdTime;
    private String identifiedCode;
}
