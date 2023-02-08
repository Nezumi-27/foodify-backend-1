package fpt.sep490.service.impl;

import fpt.sep490.entity.User;
import fpt.sep490.payload.UserDto;
import fpt.sep490.repository.UserRepository;
import fpt.sep490.service.UserService;
import org.modelmapper.ModelMapper;

public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    private ModelMapper mapper;

    public UserServiceImpl(UserRepository userRepository, ModelMapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        return null;
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
