package fpt.sep490.repository;

import fpt.sep490.entity.Category;
import fpt.sep490.entity.Product;
import fpt.sep490.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findProductsByCategoriesIn(List<Category> categories, Pageable pageable);

    Page<Product> findDistinctByCategoriesIn(List<Category> categories, Pageable pageable);
    Page<Product> findProductsByShopId(Long shopId, Pageable pageable);
    List<Product> findProductsByUsersIn(List<User> users);

    Page<Product> findProductsByUsersIn(List<User> users, Pageable pageable);
    Page<Product> findProductsByNameLikeOrNameContaining(String name, String namec, Pageable pageable);
}
