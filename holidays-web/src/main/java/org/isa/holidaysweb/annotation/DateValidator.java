package org.isa.holidaysweb.annotation;

import org.isa.holidaysweb.domain.Vacation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class DateValidator implements ConstraintValidator<ValidDates, Vacation.DatesRange> {

    @Override
    public void initialize(ValidDates constraintAnnotation) {
    }

    @Override
    public boolean isValid(Vacation.DatesRange datesRange, ConstraintValidatorContext constraintValidatorContext) {
        LocalDate dateFrom = datesRange.getDateFrom();
        LocalDate dateTo = datesRange.getDateTo();

        if (dateFrom.isBefore(LocalDate.now())) {
            return false;
        } else if (dateFrom.isBefore(dateTo) || dateFrom.isEqual(dateTo)) {
            return true;
        } else {
            return false;
        }

    }

}
