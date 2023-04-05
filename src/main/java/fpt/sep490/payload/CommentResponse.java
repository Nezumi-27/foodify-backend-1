package fpt.sep490.payload;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class CommentResponse {
    private Long id;
    private String content;
    private float rating;
    private UserDto user;

    @JsonIgnore
    private ProductDto product;
}
