package fpt.sep490.payload;

import lombok.*;

import java.sql.Timestamp;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private Long id;
    private String email;
    private String fullName;
    private String dateOfBirth;
    private String phoneNumber;
    private String imageUrl;
    private Boolean isLocked;
    private Timestamp createdTime;
    private String identifiedCode;
    private Long defaultAddress;
    private RoleDto role;
    private Set<AddressDto> addresses;
    private Set<ProductResponse> products;
    private Set<OrderResponse> orders;
}
