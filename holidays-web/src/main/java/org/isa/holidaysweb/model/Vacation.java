package org.isa.holidaysweb.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.tomcat.jni.Local;
import org.isa.holidaysweb.annotation.ValidDates;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
public class Vacation {

    private Long id;
    private boolean approved;
    private Long employeeId;
    @ValidDates
    private DatesRange datesRange;
    @Size(min=3, message="validation fail")
    private String test;

    @Data
    public static class DatesRange {
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        @Future(message = "Selected date is invalid.")
        private LocalDate dateFrom;
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        private LocalDate dateTo;
    }
}
