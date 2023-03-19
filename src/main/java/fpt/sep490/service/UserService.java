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

    StringBoolObject createLoveProduct(Long userId, Long productId);
    ProductResponsePageable getLoveProductByUserId(Long userId, int pageNo, int pageSize, String sortBy, String sortDir);

    StringBoolObject getLoveProductByUserAndProductId(Long userId, Long productId);
    StringBoolObject deleteLoveProduct(Long userId, Long productId);

    void createAddressForUser(Long userId, AddressDto addressDto);

    AddressResponse getAddressesByUser(Long userId, int pageNo, int pageSize, String sortBy, String sortDir);
    AddressDto updateUserAddress(Long userId, Long addressId, AddressDto addressDto);
    public void deleteUserAddress(Long userId, Long addressId);
}
