package fpt.sep490.service;

import fpt.sep490.payload.*;

public interface UserService {
    UserResponse createUser(UserDto userDto);
    UserResponsePageable getAllUser(int pageNo, int pageSize, String sortBy, String sortDir);

    UserResponsePageable getUsersByRoles(String roleName, int pageNo, int pageSize, String sortBy, String sortDir);

    UserResponse getUserById(Long userId);

    UserResponse getUserByEmailOrPhoneNumber(String emailOrPhoneNumber);

    UserResponse updateUser(Long userId, UserDto userDto);

    void deleteUser(Long userId);

    void createLoveProduct(Long userId, Long productId);
    ProductResponsePageable getLoveProductByUserId(Long userId, int pageNo, int pageSize, String sortBy, String sortDir);
    void deleteLoveProduct(Long userId, Long productId);

    void createAddressForUser(Long userId, AddressDto addressDto);

    AddressResponse getAddressesByUser(Long userId, int pageNo, int pageSize, String sortBy, String sortDir);

    public void deleteUserAddress(Long userId, Long addressId);
}
