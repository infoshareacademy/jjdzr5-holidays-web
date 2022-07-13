package org.isa.holidaysweb;

import lombok.RequiredArgsConstructor;
import org.isa.holidaysweb.dto.CreateUserDto;
import org.isa.holidaysweb.dto.CreateVacationDto;
import org.isa.holidaysweb.dto.UserDto;
import org.isa.holidaysweb.dto.VacationDto;
import org.isa.holidaysweb.enity.UserDAO;
import org.isa.holidaysweb.enity.VacationDAO;
import org.isa.holidaysweb.service.UserService;
import org.isa.holidaysweb.service.VacationService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DBInit implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(DBInit.class);
    private final UserService userService;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final VacationService vacationService;


    @Override
    public void run(String... args) throws Exception {

        CreateUserDto createUserDto1 = new CreateUserDto("user", passwordEncoder.encode("user"), "USER");
        userService.addUser(createUserDto1);
        CreateUserDto createUserDto2 = new CreateUserDto("admin", passwordEncoder.encode("admin"), "ADMIN");
        UserDto adminDto = userService.addUser(createUserDto2);
        UserDAO userDAO = modelMapper.map(adminDto, UserDAO.class);
        /*UUID id = userDAO.getId();
        CreateVacationDto createVacationDto = new CreateVacationDto(LocalDate.of(2022, Month.FEBRUARY, 6), LocalDate.of(2022, Month.FEBRUARY, 12), id);
        VacationDto vacationDto = vacationService.addNewVacation(createVacationDto);
        VacationDAO vacationDAO = modelMapper.map(vacationDto, VacationDAO.class);*/

        LOGGER.info("***    DB initialized!     ***");
        LOGGER.info("***  Application started!  ***");
    }
}
