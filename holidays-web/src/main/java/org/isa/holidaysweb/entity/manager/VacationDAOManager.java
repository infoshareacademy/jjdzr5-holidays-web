package org.isa.holidaysweb.entity.manager;

import org.isa.holidaysweb.entity.VacationDAO;

import java.util.UUID;

public interface VacationDAOManager {
    void save(VacationDAO vacation);

    VacationDAO find(UUID id);
}
