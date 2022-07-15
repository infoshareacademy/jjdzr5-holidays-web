package org.isa.holidaysweb.repository;

import org.isa.holidaysweb.entity.UserDAO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserDAO, UUID> {
    UserDAO findByUserName(String userName);
    Optional<UserDAO> findById(UUID uuid);
}
