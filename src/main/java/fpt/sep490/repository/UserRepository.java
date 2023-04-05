package fpt.sep490.repository;

import fpt.sep490.entity.Product;
import fpt.sep490.entity.Role;
import fpt.sep490.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Optional<User> findByPhoneNumber(String phoneNumber);

    Optional<User> findByEmailOrPhoneNumber(String email, String phoneNumber);

    Boolean existsByEmail(String email);

    Boolean existsByPhoneNumber(String phoneNumber);

    Page<User> findUsersByRole(Role role, Pageable pageable);

    Page<User> findUsersByEmailContainingOrPhoneNumberContaining(String email, String phoneNumber, Pageable pageable);

    Page<User> findUsersByRoleAndEmailContainingOrPhoneNumberContaining(Role role, String email, String phoneNumber, Pageable pageable);
}
