package fpt.sep490.payload;

import lombok.Data;

import java.util.List;

@Data
public class ProductImageResponsePageable {
    List<ProductImageDto> images;
    PageableDto page;
}
