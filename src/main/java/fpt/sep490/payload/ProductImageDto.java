package fpt.sep490.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductImageDto {
    private Long id;

    @NotEmpty(message = "Url of product image must not be empty")
    private String imageUrl;

    @NotEmpty(message = "Product id must not be empty")
    private Long productId;
}
