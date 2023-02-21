package fpt.sep490.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductImageDto {
    private Long id;
    private String imageUrl;
    private Long productId;
}
