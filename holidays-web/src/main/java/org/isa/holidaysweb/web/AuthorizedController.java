package org.isa.holidaysweb.web;

import lombok.RequiredArgsConstructor;
import org.isa.holidaysweb.api.DayOffData;
import org.isa.holidaysweb.domain.DayOff;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

@Controller
@RequiredArgsConstructor
@Secured(value = {"ROLE_USER", "ROLE_ADMIN"})
public class AuthorizedController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthorizedController.class);

    public ArrayList<DayOff> holiday = DayOffData.getDayOffList();

    @GetMapping("/admin")
    @Secured("ROLE_ADMIN")
    public String admin(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        LOGGER.info(currentPrincipalName);

        return "admin";
    }

    @GetMapping({"/", "/welcome"})
    public String welcomeHolidays(Model model) {
        model.addAttribute("holiday",  holiday);
        return "welcome";
    }
}
