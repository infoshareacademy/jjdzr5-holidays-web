package org.isa.holidaysweb.web;

import lombok.RequiredArgsConstructor;
import org.isa.holidaysweb.repository.UserRepository;
import org.isa.holidaysweb.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
@Secured(value = {"ROLE_ADMIN"})
public class AdminController {

    @Autowired
    private UserService userService;

    private static final Logger LOGGER = LoggerFactory.getLogger(AdminController.class);

    @GetMapping("/adminControlPanel")
    public String adminControlPanel() {
        return "admin-control-panel";
    }

    @GetMapping("/userManager")
    public String userManager(Model model) {
        model.addAttribute("users", userService.findAllUsersWithDetails());
        return "user-list";
    }
}
