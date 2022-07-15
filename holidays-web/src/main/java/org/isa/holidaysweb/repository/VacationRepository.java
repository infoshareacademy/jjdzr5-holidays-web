package org.isa.holidaysweb.repository;

import org.isa.holidaysweb.entity.VacationDAO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface VacationRepository extends JpaRepository<VacationDAO, UUID> {
    List<VacationDAO> findVacationDAOByUser_Id(UUID userId);
}
