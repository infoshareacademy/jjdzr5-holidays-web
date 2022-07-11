package org.isa.holidaysweb.enity.manager;

import org.isa.holidaysweb.enity.VacationDAO;

import java.util.UUID;

public interface VacationDAOManager {
    void save(VacationDAO vacation);

    VacationDAO find(UUID id);
}
