package org.isa.holidaysweb.service;

import org.isa.holidaysweb.dto.CreateUserDto;
import org.isa.holidaysweb.dto.UserDto;
import org.isa.holidaysweb.enity.UserDAO;
import org.isa.holidaysweb.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private UserRepository userRepository;

    private final ModelMapper modelMapper;

    public UserService(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public UserDto addUser(CreateUserDto createUserDto) {
        UserDAO user = new UserDAO(createUserDto.getUserName(), createUserDto.getPassword(), createUserDto.getRole());
        userRepository.save(user);
        return modelMapper.map(user, UserDto.class);
    }

    public UserDto findByUserName(String userName) {
        UserDAO user = userRepository.findByUserName(userName);
        return modelMapper.map(user, UserDto.class);
    }

    public List<UserDto> findAll() {
        List<UserDAO> users = userRepository.findAll();
        return users.stream().map(user -> modelMapper.map(user, UserDto.class)).toList();
    }
}
