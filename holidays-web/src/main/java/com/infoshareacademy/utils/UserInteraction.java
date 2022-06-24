package com.infoshareacademy.utils;

import java.time.LocalDate;
import java.util.Scanner;

public class UserInteraction {

    public static String getTypeOfHoliday() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Wpisz typ święta [National holiday, Observance, Season]: ");
        return scanner.nextLine();
    }

    public static LocalDate getStartLocalDate () {
        Scanner scanner = new Scanner(System.in);
        boolean inputIsIncorrect = true;
        LocalDate localDateFromUser = null;
        do {
            try {
                System.out.println("Podaj datę początkową w formacie yyyy-MM-dd");
                String dateInputFromUser = scanner.next();
                localDateFromUser = LocalDate.parse(dateInputFromUser);
                inputIsIncorrect = false;
            } catch (Exception e) {
                System.out.println("Format daty jest niepoprawny.");
            }
        } while (inputIsIncorrect);

        return localDateFromUser;
    }

    public static LocalDate getFinishLocalDate () {
        Scanner scanner = new Scanner(System.in);
        boolean inputIsIncorrect = true;
        LocalDate localDateFromUser = null;
        do {
            try {
                System.out.println("Podaj datę końcową w formacie yyyy-MM-dd");
                String dateInputFromUser = scanner.next();
                localDateFromUser = LocalDate.parse(dateInputFromUser);
                inputIsIncorrect = false;
            } catch (Exception e) {
                System.out.println("Format daty jest niepoprawny.");
            }
        } while (inputIsIncorrect);

        return localDateFromUser;
    }

    public static String getSearchedHolidayName() {
        Scanner scanner = new Scanner(System.in);
        String searchedName = null;
        boolean incorrectInput = true;
        do {
            try {
                System.out.println("Wpisz wyszukiwaną nazwę (minimum 3 znaki): ");
                searchedName = scanner.nextLine();
                if (searchedName.length() < 3) {
                    System.out.println("Wpisz minimum 3 znaki.");
                }
                else {
                    incorrectInput = false;
                }
            } catch (Exception e) {
                System.out.println("Niepoprawne dane.");
            }
        } while(incorrectInput);
        return searchedName;
    }
}