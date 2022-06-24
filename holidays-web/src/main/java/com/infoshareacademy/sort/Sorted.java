package com.infoshareacademy.sort;

import com.infoshareacademy.api.DayOffData;
import com.infoshareacademy.domain.DayOff;
import com.infoshareacademy.view.ConsoleView;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Properties;

public class Sorted {
    public static void sortByName() throws IOException {
        FileReader reader = new FileReader("src/main/resources/sort_name.properties");
        Properties properties = new Properties();
        properties.load(reader);
        ArrayList<DayOff> dayOffList = DayOffData.getDayOffList();
        properties.forEach((key, value) -> {
            if (key.equals("sort.desc")) {
                dayOffList.sort(Comparator.comparing(DayOff::getName));
                ConsoleView.displayList(dayOffList);
            }
            if (key.equals("sort.asc")) {
                dayOffList.sort(Comparator.comparing(DayOff::getName).reversed());
                ConsoleView.displayList(dayOffList);
            }
        });

    }
}

