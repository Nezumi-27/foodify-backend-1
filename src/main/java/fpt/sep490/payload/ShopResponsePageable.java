package fpt.sep490.payload;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShopResponsePageable {
    private List<ShopDto> shops;
    private PageableDto page;
}
