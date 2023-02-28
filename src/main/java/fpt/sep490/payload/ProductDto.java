package fpt.sep490.payload;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private Long id;
    private String name;
    private String description;
    private Boolean isEnabled;
    private float discountPercent;
    private Long cost;
    private float averageRating;
    private int reviewCount;
    private Long shopId;
    private List<Long> categoryIds;
}
