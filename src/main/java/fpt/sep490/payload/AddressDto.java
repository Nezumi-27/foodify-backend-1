package fpt.sep490.payload;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {
    private Long id;
    private String address;
    private String district;
    private String ward;
}
