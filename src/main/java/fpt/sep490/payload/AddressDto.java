package fpt.sep490.payload;

import lombok.Data;

@Data
public class AddressDto {
    private Long id;
    private String address;
    private String district;
    private String ward;
}
