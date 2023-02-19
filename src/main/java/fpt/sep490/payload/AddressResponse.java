package fpt.sep490.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.print.Pageable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressResponse {
    private List<AddressDto> addresses;
    private PageableDto page;
}
