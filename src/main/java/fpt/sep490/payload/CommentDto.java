package fpt.sep490.payload;

import lombok.Data;

@Data
public class CommentDto {
    private String content;
    private float rating;
    private Long userId;
}
