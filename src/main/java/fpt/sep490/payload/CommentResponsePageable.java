package fpt.sep490.payload;

import lombok.Data;

import java.util.List;

@Data
public class CommentResponsePageable {
    private List<CommentResponse> comments;
    private PageableDto page;
}
