package org.isa.holidaysweb.web;

import lombok.RequiredArgsConstructor;
import org.isa.holidaysweb.api.DayOffData;
import org.isa.holidaysweb.domain.DayOff;
import org.isa.holidaysweb.domain.User;
import org.isa.holidaysweb.dto.CreateUserDto;
import org.isa.holidaysweb.dto.UserDto;
import org.isa.holidaysweb.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class AnonymousController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/new_account")
    public String newAccount(@Valid @ModelAttribute User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "/signup";
        }
        CreateUserDto createUserDto = new CreateUserDto(user.getUserName(),
                passwordEncoder.encode(user.getPassword()), "USER");
        userService.addUser(createUserDto);
        model.addAttribute("userAccount", user);
        return "new_account";
    }

    @RequestMapping("/login")
    public String login(Model model) {

        return "login";
    }

    @GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }

    @GetMapping("/users")
    public String users(Model model) {
        List<UserDto> users = userService.findAll();
        model.addAttribute("users", users);
        return "users";
    }
}
