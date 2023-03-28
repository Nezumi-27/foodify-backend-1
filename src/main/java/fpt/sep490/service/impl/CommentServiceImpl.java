package fpt.sep490.service.impl;

import fpt.sep490.entity.Comment;
import fpt.sep490.entity.Product;
import fpt.sep490.entity.User;
import fpt.sep490.exception.FoodifyAPIException;
import fpt.sep490.exception.ResourceNotFoundException;
import fpt.sep490.payload.*;
import fpt.sep490.repository.CommentRepository;
import fpt.sep490.repository.ProductRepository;
import fpt.sep490.repository.UserRepository;
import fpt.sep490.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    private final ProductRepository productRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final ModelMapper mapper;


    public CommentServiceImpl(ProductRepository productRepository, CommentRepository commentRepository, UserRepository userRepository, ModelMapper mapper) {
        this.productRepository = productRepository;
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.mapper = mapper;
    }


    @Override
    public CommentResponse createComment(Long productId, CommentDto commentDto) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId));
        User user = userRepository.findById(commentDto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", commentDto.getUserId()));

        Comment comment = new Comment();
        comment.setContent(commentDto.getContent());
        comment.setRating(commentDto.getRating());
        comment.setProduct(product);
        comment.setUser(user);

        Comment newComment = commentRepository.save(comment);

        if(product.getAverageRating() == 0){
            product.setAverageRating(commentDto.getRating());
        }
        else{
            Set<Comment> comments = product.getComments();

            float sum = 0;
            int i = 0;
            for(Comment com : comments){
                sum = sum + com.getRating();
                i++;
            }

            product.setAverageRating(sum/i);
        }
        product.setReviewCount(product.getReviewCount() + 1);

        productRepository.save(product);
        return mapper.map(newComment, CommentResponse.class);
    }

    @Override
    public CommentResponsePageable getAllComments(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<Comment> comments = commentRepository.findAll(pageable);
        List<Comment> commentList = comments.getContent();
        List<CommentResponse> content = commentList.stream().map(comment -> mapper.map(comment, CommentResponse.class)).collect(Collectors.toList());

        PageableDto pageableDto = new PageableDto();
        pageableDto.setPageNo(comments.getNumber());
        pageableDto.setPageSize(comments.getSize());
        pageableDto.setTotalElements(comments.getTotalElements());
        pageableDto.setTotalPages(comments.getTotalPages());
        pageableDto.setLast(comments.isLast());

        CommentResponsePageable responses = new CommentResponsePageable();
        responses.setComments(content);
        responses.setPage(pageableDto);
        return responses;
    }

    @Override
    public CommentResponsePageable getAllCommentsByProductId(Long productId, int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId));

        Page<Comment> comments = commentRepository.findCommentsByProduct(product, pageable);
        List<Comment> commentList = comments.getContent();
        List<CommentResponse> content = commentList.stream().map(comment -> mapper.map(comment, CommentResponse.class)).collect(Collectors.toList());

        PageableDto pageableDto = new PageableDto();
        pageableDto.setPageNo(comments.getNumber());
        pageableDto.setPageSize(comments.getSize());
        pageableDto.setTotalElements(comments.getTotalElements());
        pageableDto.setTotalPages(comments.getTotalPages());
        pageableDto.setLast(comments.isLast());

        CommentResponsePageable responses = new CommentResponsePageable();
        responses.setComments(content);
        responses.setPage(pageableDto);
        return responses;
    }

    @Override
    public CommentResponse getCommentByProductIdAndUserId(Long productId, Long userId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        Comment comment = commentRepository.findCommentByProductAndUser(product, user)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", "product id & user id", productId));

        return mapper.map(comment, CommentResponse.class);
    }

    @Override
    public CommentResponse getCommentById(Long productId, Long commentId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId));

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));

        if(!comment.getProduct().getId().equals(productId)){
            throw new FoodifyAPIException(HttpStatus.BAD_REQUEST, "Comment doesn't belong to product");
        }

        return mapper.map(comment, CommentResponse.class);
    }

    @Override
    public CommentResponse updateComment(Long productId, Long commentId, CommentDto commentDto) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId));

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));

        if(!comment.getProduct().getId().equals(commentId)){
            throw new FoodifyAPIException(HttpStatus.BAD_REQUEST, "Comment doesn't belong to product");
        }

        comment.setContent(commentDto.getContent());
        comment.setRating(commentDto.getRating());
        Comment updatedComment = commentRepository.save(comment);

        if(product.getAverageRating() == 0){
            product.setAverageRating(commentDto.getRating());
        }
        else{
            Set<Comment> comments = product.getComments();

            float sum = 0;
            int i = 0;
            for(Comment com : comments){
                sum = sum + com.getRating();
                i++;
            }

            product.setAverageRating(sum/i);
        }

        productRepository.save(product);
        return mapper.map(updatedComment, CommentResponse.class);
    }

    @Override
    public StringBoolObject deleteComment(Long productId, Long commentId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId));

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));

        if(!comment.getProduct().getId().equals(productId)){
            throw new FoodifyAPIException(HttpStatus.BAD_REQUEST, "Comment doesn't belong to product");
        }

        commentRepository.delete(comment);
        if(product.getComments().isEmpty()) {
            product.setAverageRating(0);
        }
        else{
            Set<Comment> comments = product.getComments();

            float sum = 0;
            int i = 0;
            for(Comment com : comments){
                sum = sum + com.getRating();
                i++;
            }

            product.setAverageRating(sum/i);
        }

        product.setReviewCount(product.getReviewCount() - 1);
        productRepository.save(product);
        return new StringBoolObject("isDeteled", true);
    }
}
