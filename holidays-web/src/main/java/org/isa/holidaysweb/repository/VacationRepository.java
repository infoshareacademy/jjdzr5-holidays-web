package org.isa.holidaysweb.repository;

import org.isa.holidaysweb.enity.VacationDAO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface VacationRepository extends JpaRepository<VacationDAO, UUID> {
}
