package org.isa.holidaysweb.service;

public interface HolidayWebService {
    boolean isAuthenticated();
    void autoLogin(String username, String password);
}
