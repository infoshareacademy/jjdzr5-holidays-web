package org.isa.holidaysweb.web;

import org.isa.holidaysweb.model.Vacation;
import org.isa.holidaysweb.service.VacationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.naming.Binding;

@Controller
public class VacationController {
    @Autowired
    private VacationService vacationService;

    @RequestMapping("/vacationList")
    public String vacationList(Model model) {
        model.addAttribute("vacationList", vacationService.getVacationList());
        return "vacation-list";
    }

    @RequestMapping("/addNewVacation")
    public String addNewVacationForm(Model model) {
        model.addAttribute("vacation", new Vacation());
        return "add-new-vacation-form";
    }

    @PostMapping("/addNewVacation")
    public String addNewVacation(@ModelAttribute Vacation vacation, BindingResult result, Model model) {
        System.out.println("Adding new vacation to list...");
        if (result.hasErrors()) {
            System.out.println("Invalid dates");
            return ("add-new-vacation-form");
        }
        vacationService.addNewVacation(vacation);
        model.addAttribute("vacationList", vacationService.getVacationList());
        return "vacation-list";
    }
}
