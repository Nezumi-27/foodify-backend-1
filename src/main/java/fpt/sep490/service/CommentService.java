package fpt.sep490.service;

import fpt.sep490.payload.CommentDto;
import fpt.sep490.payload.CommentResponse;
import fpt.sep490.payload.CommentResponsePageable;
import fpt.sep490.payload.StringBoolObject;

public interface CommentService {
    CommentResponse createComment(Long productId, CommentDto commentDto);

    CommentResponsePageable getAllComments(int pageNo, int pageSize, String sortBy, String sortDir);

    CommentResponsePageable getAllCommentsByProductId(Long productId, int pageNo, int pageSize, String sortBy, String sortDir);

    CommentResponse getCommentByProductIdAndUserId(Long productId, Long userId);

    CommentResponse getCommentById(Long productId, Long commentId);

    CommentResponse updateComment(Long productId, Long commentId, CommentDto commentDto);

    StringBoolObject deleteComment(Long productId, Long commentId);
}
