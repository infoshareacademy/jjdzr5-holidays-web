package org.isa.holidaysweb.domain;

import lombok.*;

import java.time.LocalDate;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@SuppressWarnings("ALL")
public class DateHoliday {

    private String iso;
    private Map<String, String> datetime;
    private Map<String, String> timezone;
    public String getIso() { return iso; }
    public void setIso(String iso) { this.iso = iso; }
    public Map<String, String> getDatetime() { return datetime; }
    public void setDatetime(Map<String, String> datetime) { this.datetime = datetime; }
    public Map<String, String> getTimezone() { return timezone; }
    public void setTimezone(Map<String, String> timezone) { this.timezone = timezone; }

}
