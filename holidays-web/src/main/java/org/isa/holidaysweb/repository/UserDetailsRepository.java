package org.isa.holidaysweb.repository;

import org.isa.holidaysweb.enity.UserDetailsDAO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserDetailsRepository extends JpaRepository<UserDetailsDAO, UUID> {
    UserDetailsDAO findUserDetailsDAOByUser_Id(UUID userId);
}
