package org.isa.holidaysweb.web;

import lombok.RequiredArgsConstructor;
import org.isa.holidaysweb.domain.User;
import org.isa.holidaysweb.dto.CreateUserDto;
import org.isa.holidaysweb.dto.UserDto;
import org.isa.holidaysweb.repository.UserRepository;
import org.isa.holidaysweb.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@Secured(value = {"ROLE_ADMIN"})
public class AdminController {

    @Autowired
    private UserService userService;

    private final PasswordEncoder passwordEncoder;

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

    @GetMapping("/createNewUser")
    public String createNewUser(Model model) {
        model.addAttribute("user", new User());
        return "create-new-user-admin-form";
    }

    @PostMapping("/createNewUser")
    public String createNewUser(@ModelAttribute @Valid User user,
                                BindingResult result,
                                Model model) {
        if(result.hasErrors()) {
            return "create-new-user-admin-form";
        }
        CreateUserDto createUserDto = new CreateUserDto(user.getUserId(), user.getUserName(), passwordEncoder.encode(user.getPassword()), user.getRole());
        userService.addUser(createUserDto);
        model.addAttribute("users", userService.findAllUsersWithDetails());
        return "user-list";
    }

    @RequestMapping("/updateUser")
    public String editUser(@RequestParam UUID id, Model model) {
        LOGGER.info("Inside /updateUser @RequestMapping");
        UserDto userDto = userService.findById(id);
        LOGGER.info("Updating data for user: " + userDto.getUserName());
        LOGGER.info("Passed RequestParam: " + id);
        User user = new User();
        user.setUserId(id);
        user.setUserName(userDto.getUserName());
        user.setPassword(userDto.getPassword());
        user.setRole(userDto.getRole());
        model.addAttribute("user", user);
        return "update-user-admin-form";
    }

    @PostMapping("/updateUser")
    public String editUser(@RequestParam("userId") UUID userId,
                           @ModelAttribute @Valid User user,
                           BindingResult result,
                           Model model) {
        LOGGER.info("Inside /updateUser");
        LOGGER.info("Passed parameter: " + userId);
        if(result.hasErrors()) {
            return "update-user-admin-form";
        }
        UserDto userDto = new UserDto();
        userDto.setId(userId);
        userDto.setUserName(user.getUserName());
        userDto.setPassword(passwordEncoder.encode(user.getPassword()));
        userDto.setRole(user.getRole());
        userService.updateUserInfo(userDto);
        model.addAttribute("users", userService.findAllUsersWithDetails());
        return "user-list";
    }

    @RequestMapping("/deleteUser")
    public RedirectView deleteUser(@RequestParam UUID id) {

        userService.remove(id);
        return new RedirectView("/userManager");
    }






}
