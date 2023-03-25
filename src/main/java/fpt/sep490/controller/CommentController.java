package fpt.sep490.controller;

import fpt.sep490.payload.CommentDto;
import fpt.sep490.payload.CommentResponse;
import fpt.sep490.payload.CommentResponsePageable;
import fpt.sep490.payload.StringBoolObject;
import fpt.sep490.service.CommentService;
import fpt.sep490.utils.AppConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(value = "Comment Controller")
@RestController
@RequestMapping("/api")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @ApiOperation("Add comment")
    @PostMapping("/products/{productId}/comments")
    public ResponseEntity<CommentResponse> createComment(@PathVariable(value = "productId") Long productId,
                                                         @RequestBody CommentDto commentDto){
        return new ResponseEntity<>(commentService.createComment(productId, commentDto),  HttpStatus.CREATED);
    }

    @ApiOperation("Get All Comments")
    @GetMapping("/products/comments")
    public ResponseEntity<CommentResponsePageable> getAllComments(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_COMMENT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ){
        return ResponseEntity.ok(commentService.getAllComments(pageNo, pageSize, sortBy, sortDir));
    }

    @ApiOperation("Get All Comments By Product Id")
    @GetMapping("/products/{productId}/comments")
    public ResponseEntity<CommentResponsePageable> getAllCommentsByProduct(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_COMMENT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir,
            @PathVariable(value = "productId") Long productId
    ){
        return ResponseEntity.ok(commentService.getAllCommentsByProductId(productId, pageNo, pageSize, sortBy, sortDir));
    }

    @ApiOperation("Get Comment By Id")
    @GetMapping("/products/{productId}/comments/{commentId}")
    public ResponseEntity<CommentResponse> getCommentById(
            @PathVariable(value = "productId") Long productId,
            @PathVariable(value = "commentId") Long commentId
    ){
        return ResponseEntity.ok(commentService.getCommentById(productId, commentId));
    }

    @ApiOperation("Update Comment By Id")
    @PutMapping("/products/{productId}/comments/{commentId}")
    public ResponseEntity<CommentResponse> updateComment(
            @PathVariable(value = "productId") Long productId,
            @PathVariable(value = "commentId") Long commentId,
            @RequestBody CommentDto commentDto
    ){
        return ResponseEntity.ok(commentService.updateComment(productId, commentId, commentDto));
    }

    @ApiOperation("Delete Comment By Id")
    @DeleteMapping("/products/{productId}/comments/{commentId}")
    public ResponseEntity<StringBoolObject> deleteCommentById(
            @PathVariable(value = "productId") Long productId,
            @PathVariable(value = "commentId") Long commentId
    ){
        return ResponseEntity.ok(commentService.deleteComment(productId, commentId));
    }
}
