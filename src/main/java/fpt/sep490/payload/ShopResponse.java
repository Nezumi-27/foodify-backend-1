package fpt.sep490.payload;

import fpt.sep490.entity.User;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShopResponse {
    private List<ShopDto> shops;
    private PageableDto page;
}
