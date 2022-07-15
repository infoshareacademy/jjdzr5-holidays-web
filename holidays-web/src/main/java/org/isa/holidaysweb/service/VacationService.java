package org.isa.holidaysweb.service;



import org.isa.holidaysweb.api.Filter;
import org.isa.holidaysweb.domain.Vacation;

import org.isa.holidaysweb.dto.CreateVacationDto;
import org.isa.holidaysweb.dto.VacationDto;
import org.isa.holidaysweb.dto.VacationWithDetailsDto;
import org.isa.holidaysweb.dto.ViewVacationDto;
import org.isa.holidaysweb.entity.UserDAO;
import org.isa.holidaysweb.entity.UserDetailsDAO;
import org.isa.holidaysweb.entity.VacationDAO;
import org.isa.holidaysweb.repository.UserRepository;
import org.isa.holidaysweb.repository.VacationRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

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

    public List<ViewVacationDto> findAll() {
        LOGGER.info("Inside getVacationList method");
        List<VacationDAO> allVacations = vacationRepository.findAll();
        return mapToViewVacation(allVacations);
    }


    public List<ViewVacationDto> findUserVacation(UUID userId) {
        List<VacationDAO> vacationDAOlist = vacationRepository.findVacationDAOByUser_Id(userId);
        return mapToViewVacation(vacationDAOlist);
    }

    public List<ViewVacationDto> mapToViewVacation(List<VacationDAO> vacationDAOList) {
        List<ViewVacationDto> viewVacationDtoList = new ArrayList<>();
        for (VacationDAO vacation : vacationDAOList) {
            ViewVacationDto viewVacationDto = new ViewVacationDto(vacation.getId(), vacation.getDateFrom(), vacation.getDateTo());
            viewVacationDto.setApproved(vacation.isApproved());
            viewVacationDtoList.add(viewVacationDto);
        }
        return viewVacationDtoList;
    }

    public VacationDAO remove(UUID id) {
        LOGGER.info("Removing Vacation from db");
        VacationDAO vacation = vacationRepository.findById(id).get();
        if (vacation != null) {
            vacationRepository.delete(vacation);
            LOGGER.info("Vacation has been removed");
        } else {
            LOGGER.info("Vacation not found");
        }
        return vacation;
    }

    public List<VacationDto> findAllVacationDto() {
        List<VacationDAO> vacationsDAO = vacationRepository.findAll();
        return vacationsDAO.stream().map(v -> modelMapper.map(v, VacationDto.class)).collect(Collectors.toList());

    }

    public List<VacationWithDetailsDto> findAllVacationForManager() {
        List<VacationDAO> vacationDAOList = vacationRepository.findAll();
        List<VacationWithDetailsDto> vacationWithDetailsDtoList = new ArrayList<>();
        for (VacationDAO vacationDAO : vacationDAOList) {
            VacationWithDetailsDto vacation = new VacationWithDetailsDto();
            vacation.setVacationId(vacationDAO.getId());
            vacation.setUserId(vacationDAO.getUser().getId());
            vacation.setDateFrom(vacationDAO.getDateFrom());
            vacation.setDateTo(vacationDAO.getDateTo());
            vacation.setApproved(vacationDAO.isApproved());
            UserDetailsDAO userDetails = vacationDAO.getUser().getUserDetails();
            if(userDetails == null) {
                userDetails = new UserDetailsDAO();
            }
            vacation.setFirstName(userDetails.getFirstName());
            vacation.setLastName(userDetails.getLastName());
            vacation.setUserName(vacationDAO.getUser().getUserName());
            vacationWithDetailsDtoList.add(vacation);
        }
        return vacationWithDetailsDtoList;
    }

    public void approveVacation(UUID vacationId) {
        Optional<VacationDAO> optionalVacation = vacationRepository.findById(vacationId);
        if (optionalVacation.isPresent()) {
            VacationDAO vacation = optionalVacation.get();
            vacation.setApproved(true);
            vacationRepository.save(vacation);
        }


    }

}
