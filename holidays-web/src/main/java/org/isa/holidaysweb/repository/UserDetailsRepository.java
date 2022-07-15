package org.isa.holidaysweb.repository;

import org.isa.holidaysweb.entity.UserDetailsDAO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserDetailsRepository extends JpaRepository<UserDetailsDAO, UUID> {
    Optional<UserDetailsDAO> findUserDetailsDAOByUser_Id(UUID userId);
}
