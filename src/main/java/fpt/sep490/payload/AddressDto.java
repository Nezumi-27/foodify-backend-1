package fpt.sep490.payload;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {
    private Long id;

    @NotEmpty(message = "Address must not be empty")
    private String address;

    @NotEmpty(message = "District must not be empty")
    private String district;

    @NotEmpty(message = "Ward must not be empty")
    private String ward;
}
