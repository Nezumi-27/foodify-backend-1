package fpt.sep490.payload;

import fpt.sep490.entity.*;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Set;

@Data
public class UserResponse {
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
    private RoleDto role;
    private Set<AddressDto> addresses;
    private Set<ProductResponse> products;
    private Set<OrderResponse> orders;
}
