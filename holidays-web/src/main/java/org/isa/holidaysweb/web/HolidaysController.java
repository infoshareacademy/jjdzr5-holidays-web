package org.isa.holidaysweb.web;

import org.isa.holidaysweb.api.DayOffData;
import org.isa.holidaysweb.domain.DayOff;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.ArrayList;


@Controller
public class HolidaysController {

    public ArrayList<DayOff> holiday = DayOffData.getDayOffList();
    
    @GetMapping({"/", "/welcome"})
    public String welcomeHolidays(Model model) {
        model.addAttribute("holiday",  holiday);
        return "welcome";
    }

}
