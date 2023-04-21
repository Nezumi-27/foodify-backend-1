package fpt.sep490.payload;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class CommentDto {

    @NotEmpty(message = "Content must not be empty")
    @Size(min = 10, message = "Content should have at least 10 characters")
    private String content;

    @NotEmpty(message = "Rating must not be empty")
    @DecimalMin(value = "0", inclusive = false, message = "Rating must greater than 0 and smaller than 5")
    private float rating;

    @NotEmpty(message = "User id must not be empty")
    @DecimalMax(value = "5", inclusive = false, message = "Rating must greater than 0 and smaller than 5")
    private Long userId;
}
