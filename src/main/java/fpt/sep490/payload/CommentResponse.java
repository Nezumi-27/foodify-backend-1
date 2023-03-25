package fpt.sep490.payload;

import lombok.Data;

@Data
public class CommentResponse {
    private Long id;
    private String content;
    private float rating;
    private UserDto user;
    private ProductDto product;
}
