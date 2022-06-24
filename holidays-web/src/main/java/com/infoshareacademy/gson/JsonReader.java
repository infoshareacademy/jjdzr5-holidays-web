package com.infoshareacademy.gson;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.infoshareacademy.domain.DayOff;
import com.infoshareacademy.domain.DaysOff;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JsonReader {

    private static final Logger LOGGER = Logger.getLogger(JsonReader.class.getName());
    private static final Path FILE_JSON = Paths.get("holidays-web/src/main/resources/dayOff.json");

    private DaysOff daysOff;
    private ArrayList<DayOff> dayOffList;

    public JsonReader() {
        try {
            String json = Files.readString(FILE_JSON);
            Gson gson = GsonCreator.getGson();
            daysOff = gson.fromJson(json, DaysOff.class);
            dayOffList = daysOff.getDaysOff();
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, "File read Error");
            LOGGER.log(Level.INFO, FILE_JSON.toString());
        } catch (InvalidPathException e) {
            LOGGER.log(Level.WARNING, "Path not found");
        } catch (JsonSyntaxException jsonSyntaxException) {
            LOGGER.log(Level.WARNING, "Error parsing Json");
            dayOffList = new ArrayList<>();
            daysOff = new DaysOff();
        }
    }

    public DaysOff getDaysOff() {
        return daysOff;
    }

    public void setDaysOff(DaysOff daysOff) {
        this.daysOff = daysOff;
    }

    public ArrayList<DayOff> getDayOffList() {
        return dayOffList;
    }

    public void setDayOffList(ArrayList<DayOff> dayOffList) {
        this.dayOffList = dayOffList;
    }

    public String getJson(ArrayList<DayOff> dayOffList) {
        DaysOff daysOff = new DaysOff();
        if (dayOffList != null) {
            daysOff.setDaysOff(dayOffList);
            Gson gson = GsonCreator.getGson();

            return gson.toJson(daysOff);
        } else {
            throw new RuntimeException("Empty entry class");
        }
    }
}
