package fpt.sep490.repository;

import fpt.sep490.entity.Address;
import fpt.sep490.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Long> {
    Boolean existsByAddress(String address);
    Optional<Address> findAddressByAddress(String address);
    Page<Address> findAddressesByUsersIn(List<User> users, Pageable pageable);
}
