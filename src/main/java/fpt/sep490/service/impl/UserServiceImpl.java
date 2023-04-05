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

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.*;
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
        user.setDefaultAddress(userDto.getDefaultAddress());

        Role role = roleRepository.findByName(userDto.getRoleName())
                .orElseThrow(() -> new FoodifyAPIException(HttpStatus.NOT_FOUND, "Role doesn't found with name: " + userDto.getRoleName()));
        user.setRole(role);

        User userSaved = userRepository.save(user);
        return mapper.map(userSaved, UserResponse.class);
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
    public UserResponsePageable getUsersByEmailOrPhoneNumber(String emailOrPhoneNumber, int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<User> users = userRepository.findUsersByEmailContainingOrPhoneNumberContaining(emailOrPhoneNumber, emailOrPhoneNumber, pageable);
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
    public UserResponsePageable getUserByEmailOrPhoneNumberAndRole(String emailOrPhoneNumber, String roleName, int pageNo, int pageSize, String sortBy, String sortDir) {
        Role role = roleRepository.findByName(roleName)
                .orElseThrow(()-> new FoodifyAPIException(HttpStatus.NOT_FOUND, "Role does not found with name: " + roleName));

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<User> users = userRepository.findUsersByRoleAndEmailContainingOrPhoneNumberContaining(role , emailOrPhoneNumber, emailOrPhoneNumber, pageable);
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

        if(userDto.getDefaultAddress() != 0L){
            Address address = addressRepository.findById(userDto.getDefaultAddress())
                    .orElseThrow(() -> new ResourceNotFoundException("Address","id", userDto.getDefaultAddress()));
        }

        user.setEmail(userDto.getEmail());
        user.setFullName(userDto.getFullName());
        user.setDateOfBirth(userDto.getDateOfBirth());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setImageUrl(userDto.getImageUrl());
        user.setIsLocked(userDto.getIsLocked());
        user.setIdentifiedCode(userDto.getIdentifiedCode());
        if(userDto.getDefaultAddress() != 0L){ user.setDefaultAddress(userDto.getDefaultAddress());}
        return mapper.map(userRepository.save(user), UserResponse.class);
    }

    @Override
    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        userRepository.delete(user);
    }

    @Override
    public StringBoolObject createLoveProduct(Long productId, Long userId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        product.getUsers().add(user);
        user.getProducts().add(product);

        productRepository.save(product);
        userRepository.save(user);

        return new StringBoolObject("isSuccess", true);
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
    public StringBoolObject getLoveProductByUserAndProductId(Long userId, Long productId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        Product product = productRepository.findById(productId)
                .orElseThrow(()-> new ResourceNotFoundException("Product", "id", productId));

        List<User> users = new ArrayList<>();
        users.add(user);

        List<Product> products = productRepository.findProductsByUsersIn(users);
        List<Long> loveproductIds = products.stream().map(loveproduct -> loveproduct.getId()).collect(Collectors.toList());
        System.out.println(loveproductIds);
        if(loveproductIds.contains(productId)){
            return new StringBoolObject("Love product", true);
        }
        else{
            return new StringBoolObject("Love product", false);
        }
    }

    @Override
    public StringBoolObject deleteLoveProduct(Long productId, Long userId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        product.getUsers().remove(user);
        user.getProducts().remove(product);

        productRepository.save(product);
        userRepository.save(user);

        return new StringBoolObject("isDeleted", true);
    }

    @Override
    public StringBoolObject createAddressForUser(Long userId, AddressDto addressDto){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        boolean isHaveAddress = false;

        if(!user.getAddresses().isEmpty()){
            isHaveAddress = true;
        }
        System.out.println(isHaveAddress);

        if(addressDto.getDistrict() == "Huyện Hoàng Sa") addressDto.setWard("");

        //Check if user have that address
        Set<Address> addressSet = user.getAddresses();
        for(Address addr : addressSet){
            if(addr.getAddress().equals(addressDto.getAddress()) &&
                    addr.getDistrict().equals(addressDto.getDistrict()) &&
                    addr.getWard().equals(addressDto.getWard())) return new StringBoolObject("Address has existed", true);
        }

        if(addressRepository.existsByAddress(addressDto.getAddress())){
            List<Address> listAddress = addressRepository.findAddressesByAddress(addressDto.getAddress());

            boolean isExisted = false;

            for(Address address : listAddress){
                if(address.getDistrict().equals(addressDto.getDistrict()) && address.getWard().equals(addressDto.getWard())){
                    user.getAddresses().add(address);
                    address.getUsers().add(user);
                    if(!isHaveAddress) user.setDefaultAddress(address.getId());

                    userRepository.save(user);
                    addressRepository.save(address);

                    isExisted = true;
                    return new StringBoolObject("Create address", true);
                }
            }

            if(!isExisted){
                Address newAddress = addressRepository.save(mapper.map(addressDto, Address.class));
                newAddress.setUsers(new HashSet<>());

                newAddress.getUsers().add(user);
                user.getAddresses().add(newAddress);

                addressRepository.save(newAddress);
                if(!isHaveAddress) user.setDefaultAddress(newAddress.getId());
                userRepository.save(user);

                return new StringBoolObject("Create address", true);
            }
        }
        else {
            Address address = mapper.map(addressDto, Address.class);
            Address newAddress = addressRepository.save(address);
            newAddress.setUsers(new HashSet<>());

            newAddress.getUsers().add(user);
            user.getAddresses().add(newAddress);

            addressRepository.save(newAddress);
            if(!isHaveAddress) user.setDefaultAddress(newAddress.getId());
            userRepository.save(user);

            return new StringBoolObject("Create address", true);
        }
        return new StringBoolObject("Error", true);
    }

    @Override
    public AddressResponsePageable getAddressesByUser(Long userId, int pageNo, int pageSize, String sortBy, String sortDir) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        List<User> users = new ArrayList<>();
        users.add(user);

        Page<Address> addresses = addressRepository.findAddressesByUsersIn(users, pageable);
        List<Address> addressList = addresses.getContent();
        List<AddressResponse> content = addressList.stream().map(address -> mapper.map(address, AddressResponse.class)).collect(Collectors.toList());

        PageableDto pageableDto = new PageableDto();
        pageableDto.setPageNo(addresses.getNumber());
        pageableDto.setPageSize(addresses.getSize());
        pageableDto.setTotalElements(addresses.getTotalElements());
        pageableDto.setTotalPages(addresses.getTotalPages());
        pageableDto.setLast(addresses.isLast());

        AddressResponsePageable response = new AddressResponsePageable();
        response.setAddresses(content);
        response.setPage(pageableDto);
        return response;
    }

    @Override
    public AddressDto updateUserAddress(Long userId, Long addressId, AddressDto addressDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User", "id", userId));

        Address address = addressRepository.findById(addressId)
                .orElseThrow(()-> new ResourceNotFoundException("Address", "id", addressId));

        Set<Address> addressSet = user.getAddresses();
        for(Address addr : addressSet){
            if(addr.getAddress().equals(addressDto.getAddress()) &&
                    addr.getDistrict().equals(addressDto.getDistrict()) &&
                    addr.getWard().equals(addressDto.getWard())) throw new FoodifyAPIException(HttpStatus.BAD_REQUEST, "Address has existed");
        }

        if(addressRepository.existsByAddress(addressDto.getAddress())){
            boolean isExisted = false;
            List<Address> addresses = addressRepository.findAddressesByAddress(addressDto.getAddress());

            for(Address addr : addresses){
                if(addressDto.getDistrict().equals(addr.getDistrict()) && addressDto.getWard().equals(addr.getWard())){
                    user.getAddresses().add(addr);
                    addr.getUsers().add(user);

                    //Remove old user_address
                    user.getAddresses().remove(address);
                    address.getUsers().remove(user);

                    userRepository.save(user);
                    addressRepository.save(addr);
                    isExisted = true;
                    return mapper.map(addr, AddressDto.class);
                }
            }

            if(isExisted == false){
                Address newAddress = new Address();
                newAddress.setAddress(addressDto.getAddress());
                newAddress.setDistrict(addressDto.getDistrict());
                newAddress.setWard(addressDto.getWard());
                Address savedAddress = addressRepository.save(newAddress);
                savedAddress.setUsers(new HashSet<>());

                //Remove old user_address
                user.getAddresses().remove(address);
                address.getUsers().remove(user);
                addressRepository.save(address);
                userRepository.save(user);

                savedAddress.getUsers().add(user);
                user.getAddresses().add(savedAddress);

                //Remove old user_address
                user.getAddresses().remove(address);
                address.getUsers().remove(user);

                addressRepository.save(savedAddress);
                userRepository.save(user);

                return mapper.map(savedAddress, AddressDto.class);
            }
        }
        else {
            Address newAddress = new Address();
            newAddress.setAddress(addressDto.getAddress());
            newAddress.setDistrict(addressDto.getDistrict());
            newAddress.setWard(addressDto.getWard());
            Address savedAddress = addressRepository.save(newAddress);
            savedAddress.setUsers(new HashSet<>());

            //Remove old user_address
            user.getAddresses().remove(address);
            address.getUsers().remove(user);
            addressRepository.save(address);
            userRepository.save(user);

            savedAddress.getUsers().add(user);
            user.getAddresses().add(savedAddress);

            addressRepository.save(savedAddress);
            userRepository.save(user);

            return mapper.map(savedAddress, AddressDto.class);
        }
        return addressDto;
    }

    @Override
    public StringBoolObject updateUserDefaultAddress(Long userId, Long defaultAddressId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        Address address = addressRepository.findById(defaultAddressId)
                .orElseThrow(() -> new ResourceNotFoundException("Address", "id", defaultAddressId));

        user.setDefaultAddress(defaultAddressId);
        userRepository.save(user);
        return new StringBoolObject("Update default address", true);
    }

    @Override
    public StringBoolObject deleteUserAddress(Long userId, Long addressId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new ResourceNotFoundException("Address", "id", addressId));

        Set<Address> setAddress = user.getAddresses();

        //If address in setAddress -> return true
        for(Address addr : setAddress){
            if(addr.equals(address)){
                address.getUsers().remove(user);
                user.getAddresses().remove(address);

                addressRepository.save(address);
                userRepository.save(user);

                return new StringBoolObject("Address deleted", true);
            }
        }
        return new StringBoolObject("Address deleted", false);
    }

    @Override
    public Integer countUserRegisterByDay(int day) {
        List<User> users = userRepository.findAll();

        LocalDate today = LocalDate.now();
        LocalDate daysAgo = today.minusDays(day);

        Timestamp daysAgoTS = Timestamp.valueOf(daysAgo.atStartOfDay());

        List<User> recentList = users.stream().filter(user -> user.getCreatedTime().after(daysAgoTS)).collect(Collectors.toList());

        Integer count = recentList.stream().toArray().length;
        return count;
    }


}
