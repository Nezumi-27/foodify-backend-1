package fpt.sep490.payload;

import fpt.sep490.entity.map.Location;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShippingResponse {
    private int distance;
    private Long cost;
    private Location location;
}
