package org.isa.holidaysweb.domain;

import lombok.Getter;
import org.isa.holidaysweb.api.DateHoliday;
import org.isa.holidaysweb.domain.DayOff;
import org.isa.holidaysweb.api.Filter;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.tomcat.jni.Local;
/*import org.isa.holidaysweb.annotation.ValidDates;*/
import org.isa.holidaysweb.annotation.ValidDates;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.UUID;

@Data
@NoArgsConstructor
public class Vacation {

    @ValidDates
    private DatesRange datesRange;
    private Integer numberOfDays;
    private Object userId;

    @Data
    @NoArgsConstructor
    public static class DatesRange {
        private Long id;
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        private LocalDate dateFrom;
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        private LocalDate dateTo;
    }

    public Integer countDays() {
        Integer count = this.countDaysWithoutWeekends();
        ArrayList<DayOff> dayOffs = Filter.filterHolidayByDatesWithRange(datesRange.getDateFrom(), datesRange.getDateTo());
        for (DayOff dayOff : dayOffs) {
            DayOfWeek dayOfWeek = dayOff.getDate().refactorToLocalDate().getDayOfWeek();
            if (!(dayOfWeek.equals(DayOfWeek.SATURDAY) || dayOfWeek.equals(DayOfWeek.SUNDAY))) {
                count--;
            }
        }
        this.numberOfDays = count;
        return count;
    }

    public Integer countDaysWithoutWeekends () {
        LocalDate from = datesRange.getDateFrom();
        LocalDate to = datesRange.getDateTo();
        Integer count = 0;
        for (LocalDate date = from; !date.isEqual(to); date = date.plusDays(1)) {
            System.out.println(date.getDayOfWeek());
            if (!(date.getDayOfWeek().equals(DayOfWeek.SUNDAY) || date.getDayOfWeek().equals(DayOfWeek.SATURDAY))) {
                count++;
            }
        }
        return count;
    }
}
