package fpt.sep490.repository;

import fpt.sep490.entity.Comment;
import fpt.sep490.entity.Product;
import fpt.sep490.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Page<Comment> findCommentsByProduct(Product product, Pageable pageable);

    Optional<Comment> findCommentByProductAndUser(Product product, User user);
}
