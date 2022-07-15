package org.isa.holidaysweb.service;

import org.isa.holidaysweb.dto.CreateUserDto;
import org.isa.holidaysweb.dto.UserDetailsDto;
import org.isa.holidaysweb.dto.UserDto;
import org.isa.holidaysweb.dto.UserWithDetailsDto;
import org.isa.holidaysweb.entity.UserDAO;
import org.isa.holidaysweb.entity.UserDetailsDAO;
import org.isa.holidaysweb.repository.UserDetailsRepository;
import org.isa.holidaysweb.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    private UserDetailsRepository userDetailsRepository;

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

    public List<UserWithDetailsDto> findAllUsersWithDetails() {
        List<UserDAO> users = userRepository.findAll();
        List<UserWithDetailsDto> usersWithDetailsDto = new ArrayList<>();
        for (UserDAO user : users) {
            UserWithDetailsDto userWithDetailsDto = new UserWithDetailsDto();
            userWithDetailsDto.setUserId(user.getId());
            userWithDetailsDto.setUserName(user.getUserName());
            userWithDetailsDto.setRole(user.getRole());

            Optional<UserDetailsDAO> userDetailsOpt = userDetailsRepository.findUserDetailsDAOByUser_Id(user.getId());
            UserDetailsDAO userDetailsDAO = new UserDetailsDAO();
            if(userDetailsOpt.isPresent()) {
                userDetailsDAO = userDetailsOpt.get();
            }
            userWithDetailsDto.setFirstName(userDetailsDAO.getFirstName());
            userWithDetailsDto.setLastName(userDetailsDAO.getLastName());
            userWithDetailsDto.setDepartament(userDetailsDAO.getDepartament());

            usersWithDetailsDto.add(userWithDetailsDto);
        }
        return usersWithDetailsDto;

    }

    public UserDto findById(UUID id) {
        UserDAO userDAO = userRepository.findById(id).get();
        return modelMapper.map(userDAO, UserDto.class);
    }

    public UserDto updateUserInfo(UserDto userDto) {
        UserDAO userDAO = userRepository.findById(userDto.getId()).get();
        userDAO.setUserName(userDto.getUserName());
        userDAO.setPassword(userDto.getPassword());
        userDAO.setRole(userDto.getRole());
        userRepository.save(userDAO);
        return modelMapper.map(userDAO, UserDto.class);
    }

    public void remove(UUID id) {
        Optional<UserDAO> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            userRepository.delete(optionalUser.get());
        }

    }
}
