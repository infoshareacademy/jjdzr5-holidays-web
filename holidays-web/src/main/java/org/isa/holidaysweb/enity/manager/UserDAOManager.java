package org.isa.holidaysweb.enity.manager;

import org.isa.holidaysweb.enity.UserDAO;

import java.util.UUID;

public interface UserDAOManager {
    void save(UserDAO user);

    UserDAO find(UUID id);

}
