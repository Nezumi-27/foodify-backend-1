package fpt.sep490.payload;

import lombok.Data;

import java.util.Set;

@Data
public class RandomProductResponsePageable {
    private Set<ProductResponse> products;
    private PageableDto page;
}
