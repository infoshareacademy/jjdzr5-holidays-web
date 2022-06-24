package com.infoshareacademy.view;

import com.infoshareacademy.api.DayOffData;
import com.infoshareacademy.domain.DayOff;
import com.infoshareacademy.utils.HolidayUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class PrintDayOffs {

    public static void printDayOffByDate() {
        System.out.printf("%10s", "");
        Scanner scanner = new Scanner(System.in);
        boolean inputIsIncorrect = true;
        LocalDate localDateFromUser = null;
        do {
            try {
                System.out.print("Podaj datę w formacie yyyy-MM-dd : ");
                String dateInputFromUser = scanner.next();
                localDateFromUser = LocalDate.parse(dateInputFromUser);
                inputIsIncorrect = false;
            } catch (Exception e) {
                System.out.print("Format daty jest niepoprawny.");
            }
        } while (inputIsIncorrect);

        ArrayList<DayOff> dayOffList = DayOffData.getDayOffList();
        for (DayOff dayOff : dayOffList) {
            LocalDate iso = HolidayUtils.refactorDateHolidayToLocalDate(dayOff.getDate().getIso());
            if (iso.isEqual(localDateFromUser)) {
                ConsoleView.displayElement(dayOff);
            }
        }
    }
}
