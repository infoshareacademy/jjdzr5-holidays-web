package org.isa.holidaysweb.web;

import org.isa.holidaysweb.api.DayOffData;
import org.isa.holidaysweb.domain.DayOff;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;


@Controller
public class HolidaysController {

    public static ArrayList<DayOff> holiday = DayOffData.getDayOffList();
    @GetMapping("/welcome")
    public String welcomeHolidays(Model model) {
        DayOff dayOff = new DayOff();
        model.addAttribute("dayOff", dayOff);
        model.addAttribute("holiday",  holiday);
        return "welcome";
    }

    @PostMapping(value = "/welcome", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE, headers = "content-type=application/x-www-form-urlencoded")
        public String welcomeHolidaysPost(@ModelAttribute(value = "holiday") Model model) {
            DayOff dayOff = new DayOff();
            model.addAttribute("dayOff", dayOff);
            model.addAttribute("holiday",  holiday);
            return "welcome";
        }

}
