package fpt.sep490.payload;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private Long id;

    @NotEmpty(message = "Name of product must not be empty")
    @Size(min = 5, message = "Name of product must have more than 5 letters")
    private String name;

    @NotEmpty(message = "Description of product must be empty")
    @Size(min = 5, message = "Description of product must have more than 10 letters")
    private String description;

    @NotEmpty(message = "Description of product must not be empty")
    private Boolean isEnabled;

    @NotEmpty(message = "Cost of product must not be empty")
    private Long cost;

    @NotEmpty(message = "Shop id must not be empty")
    private Long shopId;

    @NotEmpty(message = "Description of product must not be empty")
    private List<String> categoryNames;

    private float averageRating;
    private float discountPercent;
    private int reviewCount;
}
