package fpt.sep490.payload;

import lombok.*;

import java.awt.print.Pageable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressResponse {
    private List<AddressDto> addresses;
    private PageableDto page;
}
