package fpt.sep490.service.impl;

import fpt.sep490.entity.User;
import fpt.sep490.payload.UserDto;
import fpt.sep490.repository.RoleRepository;
import fpt.sep490.repository.UserRepository;
import fpt.sep490.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    private RoleRepository roleRepository;

    private ModelMapper mapper;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, ModelMapper mapper) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.mapper = mapper;
    }

    //Create User
    @Override
    public UserDto createUser(UserDto userDto) {
        User user = mapToEntity(userDto);

        userRepository.save(user);

        UserDto userResponse = mapToDTO(user);

        return userResponse;
    }

    private User mapToEntity(UserDto userDto){
        User user = mapper.map(userDto, User.class);
        return user;
    }

    private UserDto mapToDTO(User user){
        UserDto userDto = mapper.map(user, UserDto.class);
        return userDto;
    }
}
