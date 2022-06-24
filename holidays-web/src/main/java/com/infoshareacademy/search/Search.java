package com.infoshareacademy.search;

import com.infoshareacademy.api.DayOffData;
import com.infoshareacademy.domain.DayOff;
import com.infoshareacademy.utils.UserInteraction;
import com.infoshareacademy.view.ConsoleView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Search {
    private static final List<DayOff> daysOffList = DayOffData.getDayOffList();

    public static ArrayList<DayOff> findMatchingDaysOff() {
        String searchedPhrase = UserInteraction.getSearchedHolidayName().toLowerCase();
        Stream<DayOff> daysOffStream = daysOffList.stream();
        List<DayOff> matchingDaysOff = daysOffStream.filter(dayOff -> {
            String substring = dayOff.getName().substring(0, searchedPhrase.length()).toLowerCase();
            return substring.equals(searchedPhrase);
        }).collect(Collectors.toList());

        return (ArrayList<DayOff>) matchingDaysOff;
    }
    public static void searchHolidayByName() {
        ArrayList<DayOff> matches = findMatchingDaysOff();
        ConsoleView.displayList(matches);
    }
}
