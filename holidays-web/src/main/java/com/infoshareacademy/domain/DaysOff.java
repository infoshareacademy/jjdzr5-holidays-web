package com.infoshareacademy.domain;

import java.util.ArrayList;
import java.util.Objects;

public class DaysOff {

    private ArrayList<DayOff> daysOff;

    public ArrayList<DayOff> getDaysOff() {
        return daysOff;
    }

    public void setDaysOff(ArrayList<DayOff> daysOff) {
        this.daysOff = daysOff;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DaysOff daysOff = (DaysOff) o;
        return Objects.equals(this.daysOff, daysOff.daysOff);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.daysOff);
    }

    @Override
    public String toString() {
        return "DaysOff{" +
                "daysOff=" + this.daysOff +
                '}';
    }
}