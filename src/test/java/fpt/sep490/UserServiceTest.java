package fpt.sep490;

import fpt.sep490.entity.Role;
import fpt.sep490.entity.User;
import fpt.sep490.payload.UserDto;
import fpt.sep490.payload.UserResponse;
import fpt.sep490.repository.AddressRepository;
import fpt.sep490.repository.ProductRepository;
import fpt.sep490.repository.RoleRepository;
import fpt.sep490.repository.UserRepository;
import fpt.sep490.service.UserService;
import fpt.sep490.service.impl.UserServiceImpl;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RunWith(MockitoJUnitRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private AddressRepository addressRepository;

    @Spy
    private ModelMapper mapper;

    @InjectMocks
    private UserServiceImpl userService;

    private Role role = new Role(1L, "ROLE_ANY");

    @Test
    public void stage1_testCreateUser(){
        UserDto userDto = new UserDto(
                "test@gmail.com", "0905000001", "Test name", "1-1-2001", "test.png",
                false, "123123123", "ROLE_ANY"
        );
        User user = mapper.map(userDto, User.class);

        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user);
        Mockito.when(roleRepository.findByName(Mockito.any())).thenReturn(Optional.ofNullable(role));

        UserResponse newUser = userService.createUser(userDto);

        Assert.assertNotNull(newUser);
        Assert.assertEquals("Test name", newUser.getFullName());
        Assert.assertEquals("ROLE_ANY", newUser.getRole().getRoleName());
    }

    @Test
    public void stage2_testGetAllUsers(){
        List<UserDto> userDtos = new ArrayList<>();
        userDtos.add(new UserDto("test1@gmail.com", "0905000001", "Test name 1", "1-1-2001", "test1.png",false, "123123123", "ROLE_ANY"));
        userDtos.add(new UserDto("test2@gmail.com", "0905000002", "Test name 2", "2-1-2001", "test2.png",false, "123123125", "ROLE_ANY"));
        userDtos.add(new UserDto("test3@gmail.com", "0905000003", "Test name 3", "3-1-2001", "test3.png",false, "123123120", "ROLE_ANY"));
        List<User> users = userDtos.stream().map(userDto -> mapper.map(userDto, User.class)).collect(Collectors.toList());

        int pageNumber = 0;
        int pageSize = 3;
        int fromIndex = pageNumber * pageSize;
        int toIndex = Math.min(fromIndex + pageSize, users.size());
        List<User> content = users.subList(fromIndex, toIndex);
        Page<User> page = new PageImpl<>(content);

//        Mockito.when(userRepository.findAll(Mockito.any())).thenReturn(page);
    }
}
