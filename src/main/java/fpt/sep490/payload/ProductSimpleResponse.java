package fpt.sep490.payload;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Set;

@Data
public class ProductSimpleResponse {
    private Long id;
    private String name;
    private String description;
    private float discountPercent;
    private Long cost;
    private float averageRating;
    private int reviewCount;
    private int sold;
    private ShopDto shop;
    private Set<CategoryDto> categories;
    private Set<ProductImageDto> images;

    @JsonIgnore
    private Boolean isEnabled;

    @JsonIgnore
    private CommentResponse comment;

    @JsonIgnore
    private Set<CommentResponse> comments;
}
