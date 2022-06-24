package com.infoshareacademy;

import java.time.LocalDate;
import java.util.Map;

@SuppressWarnings("ALL")
public class DateHoliday {
    public static final String RESET = "\033[0m";  // Text Reset
    public static final String GREEN = "\033[0;32m";   // GREEN
    public static final String PURPLE_BOLD = "\033[1;35m"; // PURPLE
    private String iso;
    private Map<String, String> datetime;
    private Map<String, String> timezone;

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(iso).append("\n");
        sb.append(PURPLE_BOLD + String.format("%21s", "datetime : ") + RESET).append(GREEN + datetime + RESET).append("\n");
        sb.append(PURPLE_BOLD + String.format("%21s", "timezone : ") + RESET).append(GREEN + timezone + RESET).append("\n");
        return sb.toString();
    }
    public String getIso() { return iso; }
    public void setIso(String iso) { this.iso = iso; }
    public Map<String, String> getDatetime() { return datetime; }
    public void setDatetime(Map<String, String> datetime) { this.datetime = datetime; }
    public Map<String, String> getTimezone() { return timezone; }
    public void setTimezone(Map<String, String> timezone) { this.timezone = timezone; }
    public LocalDate refactorToLocalDate () {
        LocalDate localDate = LocalDate.parse(this.iso.substring(0, 10));
        return localDate;
    }
}
