package fpt.sep490.service.impl;

import fpt.sep490.entity.Product;
import fpt.sep490.exception.ResourceNotFoundException;
import fpt.sep490.payload.CommentDto;
import fpt.sep490.payload.CommentResponse;
import fpt.sep490.payload.CommentResponsePageable;
import fpt.sep490.payload.StringBoolObject;
import fpt.sep490.repository.CommentRepository;
import fpt.sep490.repository.ProductRepository;
import fpt.sep490.service.CommentService;
import org.modelmapper.ModelMapper;

public class CommentServiceImpl implements CommentService {
    private ProductRepository productRepository;
    private CommentRepository commentRepository;
    private ModelMapper mapper;

    public CommentServiceImpl(ProductRepository productRepository, CommentRepository commentRepository, ModelMapper mapper) {
        this.productRepository = productRepository;
        this.commentRepository = commentRepository;
        this.mapper = mapper;
    }


    @Override
    public CommentResponse createComment(Long productId, CommentDto commentDto) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId));

        return null;
    }

    @Override
    public CommentResponsePageable getAllComments() {
        return null;
    }

    @Override
    public CommentResponsePageable getAllCommentsByProductId(Long productId) {
        return null;
    }

    @Override
    public CommentResponse getCommentById(Long productId, Long commentId) {
        return null;
    }

    @Override
    public CommentResponse updateComment(Long productId, Long commentId, CommentDto commentDto) {
        return null;
    }

    @Override
    public StringBoolObject deleteComment(Long productId, Long commentId) {
        return null;
    }
}
