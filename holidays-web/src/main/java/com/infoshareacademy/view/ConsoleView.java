package com.infoshareacademy.view;

import com.infoshareacademy.api.DayOffData;
import com.infoshareacademy.domain.DayOff;
import de.vandermeer.asciitable.AT_Row;
import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.asciithemes.u8.U8_Grids;
import de.vandermeer.skb.interfaces.transformers.textformat.TextAlignment;

import java.util.ArrayList;

public class ConsoleView {

    public static void displayList(ArrayList<DayOff> dayOffList) {
        AsciiTable asciiTable = new AsciiTable();
        asciiTable.addRule();
        AT_Row header = asciiTable.addRow("Name", "Description", "Country", "Date", "Type", "Locations", "Sates");
        header.setTextAlignment(TextAlignment.CENTER);
        asciiTable.addRule();
        for (DayOff dayOff : dayOffList) {
            AT_Row row = asciiTable.addRow(
                    dayOff.getName(),
                    dayOff.getDescription(),
                    dayOff.getCountry().get("name"),
                    dayOff.getDate().getIso(),
                    dayOff.getType()[0],
                    dayOff.getLocations(),
                    dayOff.getStates()
            );
            row.setTextAlignment(TextAlignment.CENTER);
            asciiTable.addRule();
        }
        asciiTable.getContext().setWidth(180);

        asciiTable.getContext().setGrid(U8_Grids.borderDoubleLight());
        System.out.println(asciiTable.render());
    }
    public static void displayElement(DayOff id) {
        AsciiTable asciiTable = new AsciiTable();
        asciiTable.addRule();
        AT_Row header = asciiTable.addRow("Name", "Description", "Country", "Date", "Type", "Locations", "Sates");
        header.setTextAlignment(TextAlignment.CENTER);
        asciiTable.addRule();
        AT_Row row = asciiTable.addRow(
                id.getName(),
                id.getDescription(),
                id.getCountry().get("name"),
                id.getDate().getIso(),
                id.getType()[0],
                id.getLocations(),
                id.getStates()
        );
        row.setTextAlignment(TextAlignment.CENTER);
        asciiTable.addRule();
        asciiTable.getContext().setWidth(180);
        asciiTable.getContext().setGrid(U8_Grids.borderDoubleLight());
        System.out.println(asciiTable.render());
    }

    public static void calendarPrint() {
        ArrayList<DayOff> dayOffList = DayOffData.getDayOffList();
        ConsoleView.displayList(dayOffList);
    }
}