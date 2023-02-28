package fpt.sep490.payload;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShipperDto {
    private Long id;
    private Long userId;
    private Long shopId;
    private Boolean isShipping;
}
