package com.infoshareacademy;

public class VacationApplication {

    private int $id;


//    getCountOfDayOf(dayOff) {}



    private int idHoliday, idEmployee;

    /*public Holiday(int idHoliday, int idEmployee) {
        this.idHoliday = idHoliday;
        this.idEmployee = idEmployee;
    }*/

    public int getIdHoliday() {
        return idHoliday;
    }

    public void setIdHoliday(int idHoliday) {
        this.idHoliday = idHoliday;
    }

    public int getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(int idEmployee) {
        this.idEmployee = idEmployee;
    }
}


//id | $dataFrom | dataTo | count -> $dateFrom - $dateTo