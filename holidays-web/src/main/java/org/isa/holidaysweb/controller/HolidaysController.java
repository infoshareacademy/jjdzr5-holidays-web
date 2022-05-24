package org.isa.holidaysweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HolidaysController {
    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }
}

