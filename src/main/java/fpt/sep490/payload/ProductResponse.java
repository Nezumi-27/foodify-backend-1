package fpt.sep490.payload;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.math.BigInteger;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {
    private Long id;
    private String name;
    private String description;
    private Boolean isEnabled;
    private float discountPercent;
    private Long cost;
    private float averageRating;
    private int reviewCount;
    private ShopDto shop;
    private Set<CategoryDto> categories;
    private Set<ProductImageDto> images;
    private CommentResponse comment;

    @JsonIgnore
    private Set<CommentResponse> comments;
}
