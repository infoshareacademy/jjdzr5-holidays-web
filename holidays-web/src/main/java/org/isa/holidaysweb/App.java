package org.isa.holidaysweb;

import com.infoshareacademy.filter.Filter;
import com.infoshareacademy.view.ConsoleView;

import java.time.LocalDate;
import java.time.Month;

public class App {
    public static void main(String[] args) {
        Integer count = Filter.filterHolidayByDatesWithRange(LocalDate.of(2022, Month.DECEMBER, 01), LocalDate.of(2022, Month.DECEMBER, 31)).size();
        System.out.println(count);
    }
}
