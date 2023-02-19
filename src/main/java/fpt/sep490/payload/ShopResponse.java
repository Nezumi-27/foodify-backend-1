package fpt.sep490.payload;

import fpt.sep490.entity.User;
import lombok.Data;

import java.util.List;

@Data
public class ShopResponse {
    private List<ShopDto> shops;
    private PageableDto page;
}
