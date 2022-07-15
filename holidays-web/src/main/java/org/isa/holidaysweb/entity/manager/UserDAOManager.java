package org.isa.holidaysweb.entity.manager;

import org.isa.holidaysweb.entity.UserDAO;

import java.util.UUID;

public interface UserDAOManager {
    void save(UserDAO user);

    UserDAO find(UUID id);

}
