package org.isa.holidaysweb;

import com.infoshareacademy.filter.Filter;
import com.infoshareacademy.view.ConsoleView;
import org.apache.tomcat.jni.Local;
import org.isa.holidaysweb.model.Vacation;

import java.time.LocalDate;
import java.time.Month;

public class App {
    public static void main(String[] args) {

        LocalDate from = LocalDate.of(2022, Month.DECEMBER, 1);
        LocalDate to = LocalDate.of(2022, Month.DECEMBER, 31);

        ConsoleView.displayList(Filter.filterHolidayByDatesWithRange(from, to));

        Vacation vacation = new Vacation();
        Vacation.DatesRange datesRange = new Vacation.DatesRange();
        datesRange.setDateFrom(from);
        datesRange.setDateTo(to);
        vacation.setDatesRange(datesRange);
        Integer integer = vacation.countDays();
        System.out.println(integer);
    }
}
