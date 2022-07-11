package org.isa.holidaysweb.api;

import java.time.LocalDate;
import java.time.Month;

public class AppTest {

    public static void main(String[] args) {
        LocalDate from = LocalDate.of(2022, Month.DECEMBER, 1);
        LocalDate to = LocalDate.of(2022, Month.DECEMBER, 31);
        System.out.println(Filter.filterHolidayByDatesWithRange(from, to));
    }
}
