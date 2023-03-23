package fpt.sep490.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressResponse {
    private Long id;

    @NotEmpty(message = "Address must not be empty")
    private String address;

    @NotEmpty(message = "District must not be empty")
    private String district;

    @NotEmpty(message = "Ward must not be empty")
    private String ward;

    private Set<UserDto> users;

    private Set<OrderDto> orders;
}
