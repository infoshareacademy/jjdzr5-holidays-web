package org.isa.holidaysweb.service;

import org.isa.holidaysweb.model.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);

}
