package org.isa.holidaysweb.service;



import org.isa.holidaysweb.api.Filter;
import org.isa.holidaysweb.domain.Vacation;

import org.isa.holidaysweb.dto.CreateVacationDto;
import org.isa.holidaysweb.dto.VacationDto;
import org.isa.holidaysweb.enity.UserDAO;
import org.isa.holidaysweb.enity.VacationDAO;
import org.isa.holidaysweb.repository.UserRepository;
import org.isa.holidaysweb.repository.VacationRepo;
import org.isa.holidaysweb.repository.VacationRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class VacationService {
    private static final Logger LOGGER = LoggerFactory.getLogger(VacationService.class);

    private VacationRepository vacationRepository;
    private final ModelMapper modelMapper;
    @Autowired
    private UserRepository userRepository;

    public VacationService(VacationRepository vacationRepository, ModelMapper modelMapper) {
        this.vacationRepository = vacationRepository;
        this.modelMapper = modelMapper;
    }

    public Integer createNewVacationRequest(Vacation vacation) {
        LocalDate dateFrom = vacation.getDatesRange().getDateFrom();
        LocalDate dateTo = vacation.getDatesRange().getDateTo();
        Integer bankHolidaysInRange = Filter.filterHolidayByDatesWithRange(dateFrom, dateTo).size();
        return bankHolidaysInRange;
    }

    public VacationDto addNewVacation(CreateVacationDto vacation) {
        LOGGER.info("Inside addNewVacationMethod");
        LOGGER.info("Adding new vacation: " + vacation);

        UserDAO user = userRepository.findById(vacation.getUserId()).get();
        VacationDAO vacationDAO = new VacationDAO(vacation.getDateFrom(), vacation.getDateTo(), user);
        vacationRepository.save(vacationDAO);
        return modelMapper.map(vacationDAO, VacationDto.class);
    }

    public List<VacationDAO> findAll() {
        System.out.println("Inside getVacationList method");
        return vacationRepository.findAll();
    }

}
