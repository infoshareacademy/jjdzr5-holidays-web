package org.isa.holidaysweb.api;

import org.isa.holidaysweb.domain.DayOff;
import org.isa.holidaysweb.gson.JsonReader;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;


public class DayOffData {

    public static ArrayList<DayOff> getDayOffList() {
        JsonReader jsonReader = new JsonReader();
        return jsonReader.getDayOffList();
    }
}
