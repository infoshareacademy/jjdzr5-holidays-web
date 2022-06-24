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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.naming.Binding;
import javax.validation.Valid;

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
        if(!model.containsAttribute("vacation")) {
            model.addAttribute("vacation", new Vacation());
        }
        return "add-new-vacation-form";
    }

    @PostMapping("/addNewVacation")
    public RedirectView addNewVacation(@ModelAttribute @Valid Vacation vacation,
                                       BindingResult result,
                                       RedirectAttributes redirectAttributes,
                                       Model model) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.vacation", result);
            redirectAttributes.addFlashAttribute("vacation", vacation);
            return new RedirectView("/addNewVacation");
        }
        vacationService.addNewVacation(vacation);
        model.addAttribute("vacationList", vacationService.getVacationList());
        return new RedirectView("/vacationList");
    }

}
