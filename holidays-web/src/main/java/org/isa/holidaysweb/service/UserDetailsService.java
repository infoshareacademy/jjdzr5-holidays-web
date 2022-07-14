package org.isa.holidaysweb.service;

import org.isa.holidaysweb.domain.UserDetails;
import org.isa.holidaysweb.dto.CreateUserDetailsDto;
import org.isa.holidaysweb.dto.UserDetailsDto;
import org.isa.holidaysweb.enity.UserDAO;
import org.isa.holidaysweb.enity.UserDetailsDAO;
import org.isa.holidaysweb.repository.UserDetailsRepository;
import org.isa.holidaysweb.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserDetailsService.class);

    @Autowired
    private UserRepository userRepository;

    private UserDetailsRepository userDetailsRepository;
    private final ModelMapper modelMapper;

    public UserDetailsService(UserDetailsRepository userDetailsRepository, ModelMapper modelMapper) {
        this.userDetailsRepository = userDetailsRepository;
        this.modelMapper = modelMapper;
    }

    public UserDetailsDto createUserDetails(CreateUserDetailsDto userDetailsDto) {
        LOGGER.info("Adding user information.");
        UserDAO user = userRepository.findById(userDetailsDto.getUserId()).get();
        UserDetailsDAO userDetailsDAO = new UserDetailsDAO(userDetailsDto.getFirstName(), userDetailsDto.getLastName(), userDetailsDto.getDepartament(), user, userDetailsDto.getProfilePicture());
        userDetailsRepository.save(userDetailsDAO);
        return modelMapper.map(userDetailsDAO, UserDetailsDto.class);
    }

    public UserDetailsDto updateUserDetails(UserDetailsDto userDetailsDto, UUID userId) {
        UserDetailsDAO userDetailsDAO = userDetailsRepository.findUserDetailsDAOByUser_Id(userId).get();
        LOGGER.info("Found UserDetailsDAO: " + userDetailsDAO.getFirstName() + " " + userDetailsDAO.getLastName());
        userDetailsDAO.setFirstName(userDetailsDto.getFirstName());
        userDetailsDAO.setLastName(userDetailsDto.getLastName());
        userDetailsDAO.setDepartament(userDetailsDto.getDepartament());
        userDetailsDAO.setProfilePicture(userDetailsDto.getProfilePicture());
        userDetailsRepository.save(userDetailsDAO);
        return modelMapper.map(userDetailsDAO, UserDetailsDto.class);
    }

}
