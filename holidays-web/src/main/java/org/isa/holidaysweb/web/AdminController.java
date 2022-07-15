package org.isa.holidaysweb.web;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
@Secured(value = {"ROLE_ADMIN"})
public class AdminController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AdminController.class);

    @GetMapping("/adminControlPanel")
    public String adminControlPanel() {
        return "admin-control-panel";
    }
}
