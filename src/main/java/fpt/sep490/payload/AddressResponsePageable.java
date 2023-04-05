package fpt.sep490.payload;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressResponsePageable {
    private List<AddressResponse> addresses;
    private PageableDto page;
}
