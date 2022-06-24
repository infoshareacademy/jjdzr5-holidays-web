package com.infoshareacademy.utils;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class HolidayUtils {
    public static LocalDate refactorDateHolidayToLocalDate(String iso) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(iso.substring(0,10), formatter);
    }

}
