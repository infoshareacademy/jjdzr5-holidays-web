package com.infoshareacademy;

@SuppressWarnings("ALL")
public enum Department {
    DEPARTMENT_1("Dział 1"),
    DEPARTMENT_2("Dział 2"),
    DEPARTMENT_3("Dział 3"),
    DEPARTMENT_4("Dział 4"),
    DEPARTMENT_5("Dział 5"),
    DEPARTMENT_6("Dział 6"),
    DEPARTMENT_7("Dział 7"),
    DEPARTMENT_8("Dział 8"),
    DEPARTMENT_9("Dział 9"),
    DEPARTMENT_10("Dział 10");

    private final String name;
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String RED_BOLD = "\033[1;31m";    // RED

    Department(String name) { this.name = name; }
    public String getName() { return RED_BOLD + name + ANSI_RESET; }
    public static String getDepartment(int department) {
        for(Department d : Department.values()) {
            if(d.ordinal() == (department - 1)) {
                return d.getName();
            }
        }
        return null;
    }
}
