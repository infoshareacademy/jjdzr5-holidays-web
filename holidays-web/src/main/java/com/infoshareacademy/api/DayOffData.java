package com.infoshareacademy.api;

import com.infoshareacademy.domain.DayOff;
import com.infoshareacademy.gson.JsonReader;

import java.util.ArrayList;

public class DayOffData {

    public static ArrayList<DayOff> getDayOffList() {
        JsonReader jsonReader = new JsonReader();
        return jsonReader.getDayOffList();
    }
}
