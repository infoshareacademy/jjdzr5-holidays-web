package org.isa.holidaysweb.enity;

public enum Departament {
    SALES("Sales"),
    INVESTMENT("Investment"),
    BACKOFFICE("Back Office"),
    PRODUCT("Product");

    private final String displayValue;

    private Departament(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }

}
