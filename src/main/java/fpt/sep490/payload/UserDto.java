package fpt.sep490.payload;

import fpt.sep490.entity.Address;
import fpt.sep490.entity.Role;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;

public class UserDto {
    private Long id;
    private String username;
    private String password;
    private String firstname;
    private String lastName;
    private Date dateOfBirth;
    private String phoneNumber;
    private String email;
    private boolean isLocked;
    private Timestamp createdTime;
    private String identifiedCode;
//    private Role role;
//    private Set<Address> addresses;
}
