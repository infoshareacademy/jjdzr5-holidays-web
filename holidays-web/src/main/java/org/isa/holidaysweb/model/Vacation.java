package org.isa.holidaysweb.model;

import com.infoshareacademy.DateHoliday;
import com.infoshareacademy.domain.DayOff;
import com.infoshareacademy.filter.Filter;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.tomcat.jni.Local;
/*import org.isa.holidaysweb.annotation.ValidDates;*/
import org.isa.holidaysweb.annotation.ValidDates;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
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

    private Long id;
    private boolean approved;
    private Long employeeId;
    @ValidDates
    private DatesRange datesRange;
    private Integer numberOfdays;

    @Data
    public static class DatesRange {
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
        this.numberOfdays = count;
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
