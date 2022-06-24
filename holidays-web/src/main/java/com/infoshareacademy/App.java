package com.infoshareacademy;

import com.infoshareacademy.filter.Filter;
import com.infoshareacademy.search.Search;
import com.infoshareacademy.sort.Sorted;
import com.infoshareacademy.view.ConsoleView;
import com.infoshareacademy.view.PrintDayOffs;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;


@SuppressWarnings("ALL")
public class App {
    private String startDate;
    private String endDate;
    String etc = "";

    public static void main(String[] args) throws IOException, ParseException {
        // write your code here
        new App().window();
    }

    public void window() throws IOException, ParseException {
        while (true) {
        System.out.printf("\n%10s*************************************************************\n", etc);
        System.out.printf("%10s*\t                   Witaj w Holidays App%19s*", etc, etc);
        System.out.printf("\n%10s*************************************************************\n", etc);
        System.out.printf("%10s*---------------------!!  MENU GŁÓWNE  !!-------------------*\n", etc);
        System.out.printf("%10s*\t1. Pokaż kalendarz dni wolnych%28s*\n", etc, etc);
        System.out.printf("%10s*\t2. Posortuj listę dni wolnych po nazwie %18s*\n", etc, etc);
        System.out.printf("%10s*\t3. Wyświetl dzień wolny po dacie%26s*\n", etc, etc);
        System.out.printf("%10s*\t4. Filtrowanie dni wolnych po zakresie dat%16s*\n", etc, etc);
        System.out.printf("%10s*\t5. Filtrowanie dni wolnych po typie dnia wolnego%10s*\n", etc, etc);
        System.out.printf("%10s*\t6. Szukaj dnia wolnego po nazwie%26s*\n", etc, etc);
        System.out.printf("%10s*\t0. Wyjdź %49s*", etc, etc);
        System.out.printf("\n%10s*************************************************************\n", etc);
        System.out.printf("%10s*\tWybierz opcję 0 - 6 : ", etc);
        int option = new Scanner(System.in).nextInt();
        switch (option) {
            case 1 -> ConsoleView.calendarPrint();
            case 2 -> Sorted.sortByName();
            case 3 -> PrintDayOffs.printDayOffByDate();
            case 4 -> ConsoleView.displayList(Filter.filterHolidayByDates());
            case 5 -> ConsoleView.displayList(Filter.filterHolidayByType());
            case 6 -> Search.searchHolidayByName();
            case 0 -> System.exit(0);
            default -> System.out.println("Zła opcja, wybierz inną.");
            }
        }
    }
}
