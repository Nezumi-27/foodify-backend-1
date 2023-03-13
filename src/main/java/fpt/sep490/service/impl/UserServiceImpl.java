package fpt.sep490.service.impl;

import fpt.sep490.entity.Address;
import fpt.sep490.entity.Product;
import fpt.sep490.entity.Role;
import fpt.sep490.entity.User;
import fpt.sep490.exception.FoodifyAPIException;
import fpt.sep490.exception.ResourceNotFoundException;
import fpt.sep490.payload.*;
import fpt.sep490.repository.AddressRepository;
import fpt.sep490.repository.ProductRepository;
import fpt.sep490.repository.RoleRepository;
import fpt.sep490.repository.UserRepository;
import fpt.sep490.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private ProductRepository productRepository;
    private ModelMapper mapper;
    private AddressRepository addressRepository;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, ProductRepository productRepository, ModelMapper mapper,
                           AddressRepository addressRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.productRepository = productRepository;
        this.mapper = mapper;
        this.addressRepository = addressRepository;
    }


    @Override
    public UserResponse createUser(UserDto userDto) {
        if(userRepository.existsByEmail(userDto.getEmail())){
            throw new FoodifyAPIException(HttpStatus.BAD_REQUEST, "Email is already taken");
        }

        if(userRepository.existsByPhoneNumber(userDto.getPhoneNumber())){
            throw new FoodifyAPIException(HttpStatus.BAD_REQUEST, "Phone number is already taken");
        }

        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setFullName(userDto.getFullName());
        user.setDateOfBirth(userDto.getDateOfBirth());
        user.setImageUrl(userDto.getImageUrl());
        user.setIdentifiedCode(userDto.getIdentifiedCode());
        user.setIsLocked(userDto.getIsLocked());

        Role role = roleRepository.findByName(userDto.getRoleName())
                .orElseThrow(() -> new FoodifyAPIException(HttpStatus.NOT_FOUND, "Role doesn't found with name: " + userDto.getRoleName()));
        user.setRole(role);

        userRepository.save(user);
        return mapper.map(user, UserResponse.class);
    }

    @Override
    public UserResponsePageable getAllUser(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<User> users = userRepository.findAll(pageable);
        List<User> userList = users.getContent();
        List<UserResponse> content = userList.stream().map(user -> mapper.map(user, UserResponse.class)).collect(Collectors.toList());

        PageableDto pageableDto = new PageableDto();
        pageableDto.setPageNo(users.getNumber());
        pageableDto.setPageSize(users.getSize());
        pageableDto.setTotalElements(users.getTotalElements());
        pageableDto.setTotalPages(users.getTotalPages());
        pageableDto.setLast(users.isLast());

        UserResponsePageable response = new UserResponsePageable();
        response.setUsers(content);
        response.setPage(pageableDto);

        return response;
    }

    @Override
    public UserResponsePageable getUsersByRoles(String roleName, int pageNo, int pageSize, String sortBy, String sortDir) {
        Role role = roleRepository.findByName(roleName)
                .orElseThrow(()-> new FoodifyAPIException(HttpStatus.NOT_FOUND, "Role does not found with name: " + roleName));

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<User> users = userRepository.findUsersByRole(role, pageable);
        List<User> userList = users.getContent();
        List<UserResponse> content = userList.stream().map(user -> mapper.map(user, UserResponse.class)).collect(Collectors.toList());

        PageableDto pageableDto = new PageableDto();
        pageableDto.setPageNo(users.getNumber());
        pageableDto.setPageSize(users.getSize());
        pageableDto.setTotalElements(users.getTotalElements());
        pageableDto.setTotalPages(users.getTotalPages());
        pageableDto.setLast(users.isLast());

        UserResponsePageable response = new UserResponsePageable();
        response.setUsers(content);
        response.setPage(pageableDto);

        return response;
    }

    @Override
    public UserResponse getUserById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        return mapper.map(user, UserResponse.class);
    }

    @Override
    public UserResponse getUserByEmailOrPhoneNumber(String emailOrPhoneNumber) {
        User user = userRepository.findByEmailOrPhoneNumber(emailOrPhoneNumber, emailOrPhoneNumber)
                .orElseThrow(() -> new FoodifyAPIException(HttpStatus.BAD_REQUEST, "User can not found with email or phone number: " + emailOrPhoneNumber));

        return mapper.map(user, UserResponse.class);
    }

    @Override
    public UserResponse updateUser(Long userId, UserDto userDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        user.setEmail(userDto.getEmail());
        user.setFullName(userDto.getFullName());
        user.setDateOfBirth(userDto.getDateOfBirth());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setImageUrl(userDto.getImageUrl());
        user.setIsLocked(userDto.getIsLocked());
        user.setIdentifiedCode(userDto.getIdentifiedCode());

        return mapper.map(userRepository.save(user), UserResponse.class);
    }

    @Override
    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        userRepository.delete(user);
    }

    @Override
    public void createLoveProduct(Long productId, Long userId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        product.getUsers().add(user);
        user.getProducts().add(product);

        productRepository.save(product);
        userRepository.save(user);
    }

    @Override
    public ProductResponsePageable getLoveProductByUserId(Long userId, int pageNo, int pageSize, String sortBy, String sortDir) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        List<User> users = new ArrayList<>();
        users.add(user);

        Page<Product> products = productRepository.findProductsByUsersIn(users, pageable);
        List<Product> productList = products.getContent();
        List<ProductResponse> content = productList.stream().map(product -> mapper.map(product, ProductResponse.class)).collect(Collectors.toList());

        PageableDto pageableDto = new PageableDto();
        pageableDto.setPageNo(products.getNumber());
        pageableDto.setPageSize(products.getSize());
        pageableDto.setTotalElements(products.getTotalElements());
        pageableDto.setTotalPages(products.getTotalPages());
        pageableDto.setLast(products.isLast());

        ProductResponsePageable responsePageable = new ProductResponsePageable();
        responsePageable.setProducts(content);
        responsePageable.setPage(pageableDto);

        return responsePageable;
    }

    @Override
    public void deleteLoveProduct(Long productId, Long userId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        product.getUsers().remove(user);
        user.getProducts().remove(product);

        productRepository.save(product);
        userRepository.save(user);
    }

    @Override
    public void createAddressForUser(Long userId, AddressDto addressDto){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        if(addressRepository.existsByAddress(addressDto.getAddress())){
            Address address = addressRepository.findAddressByAddress(addressDto.getAddress())
                    .orElseThrow(() -> new FoodifyAPIException(HttpStatus.NOT_FOUND, "Address not found"));

            user.getAddresses().add(address);
            address.getUsers().add(user);

            userRepository.save(user);
            addressRepository.save(address);
        }
        else {
            Address address = mapper.map(addressDto, Address.class);
            Address newAddress = addressRepository.save(address);
            newAddress.setUsers(new HashSet<>());

            newAddress.getUsers().add(user);
            user.getAddresses().add(newAddress);

            addressRepository.save(newAddress);
            userRepository.save(user);
        }
    }

    @Override
    public AddressResponse getAddressesByUser(Long userId, int pageNo, int pageSize, String sortBy, String sortDir) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        List<User> users = new ArrayList<>();
        users.add(user);

        Page<Address> addresses = addressRepository.findAddressesByUsersIn(users, pageable);
        List<Address> addressList = addresses.getContent();
        List<AddressDto> content = addressList.stream().map(address -> mapper.map(address, AddressDto.class)).collect(Collectors.toList());

        PageableDto pageableDto = new PageableDto();
        pageableDto.setPageNo(addresses.getNumber());
        pageableDto.setPageSize(addresses.getSize());
        pageableDto.setTotalElements(addresses.getTotalElements());
        pageableDto.setTotalPages(addresses.getTotalPages());
        pageableDto.setLast(addresses.isLast());

        AddressResponse response = new AddressResponse();
        response.setAddresses(content);
        response.setPage(pageableDto);
        return response;
    }

    @Override
    public void deleteUserAddress(Long userId, Long addressId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new ResourceNotFoundException("Address", "id", addressId));

        address.getUsers().remove(user);
        user.getAddresses().remove(address);

        addressRepository.save(address);
        userRepository.save(user);
    }


}
