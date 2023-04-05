package fpt.sep490.payload;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShipperResponsePageable {
    private List<ShipperResponse> shippers;
    private PageableDto page;
}
