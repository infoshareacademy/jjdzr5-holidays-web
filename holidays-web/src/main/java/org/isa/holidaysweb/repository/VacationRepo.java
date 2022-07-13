package org.isa.holidaysweb.repository;

import org.isa.holidaysweb.domain.Vacation;


import java.util.ArrayList;
import java.util.List;

public class VacationRepo {

    public static List<Vacation> vacationList = new ArrayList<>();

    public static List<Vacation> getVacationList() {
        return vacationList;
    }
}
