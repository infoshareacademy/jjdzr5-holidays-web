package org.isa.holidaysweb.service;

import org.isa.holidaysweb.model.Vacation;
import org.isa.holidaysweb.repository.VacationRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Digits;
import java.util.List;

@Component
public class VacationService {

    public void addNewVacation(Vacation vacation) {
        System.out.println("Inside addNewVacationMethod");
        System.out.println("Adding new vacation: " + vacation);
        List<Vacation> vacationList = VacationRepository.getVacationList();
        vacationList.add(vacation);
    }

    public List<Vacation> getVacationList() {
        System.out.println("Inside getVacationList method");
        return VacationRepository.getVacationList();
    }

}
