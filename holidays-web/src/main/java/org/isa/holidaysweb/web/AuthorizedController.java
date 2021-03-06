package org.isa.holidaysweb.web;

import lombok.RequiredArgsConstructor;
import org.isa.holidaysweb.api.DayOffData;
import org.isa.holidaysweb.config.userlogging.UserPrincipal;
import org.isa.holidaysweb.domain.DayOff;
import org.isa.holidaysweb.domain.UserDetails;
import org.isa.holidaysweb.domain.Vacation;
import org.isa.holidaysweb.dto.CreateUserDetailsDto;
import org.isa.holidaysweb.dto.CreateVacationDto;
import org.isa.holidaysweb.dto.UserDetailsDto;
import org.isa.holidaysweb.entity.UserDetailsDAO;
import org.isa.holidaysweb.entity.VacationDAO;
import org.isa.holidaysweb.repository.UserDetailsRepository;
import org.isa.holidaysweb.repository.VacationRepository;
import org.isa.holidaysweb.service.UserDetailsService;
import org.isa.holidaysweb.service.VacationService;
import org.isa.holidaysweb.utils.FileUploadUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@Secured(value = {"ROLE_USER", "ROLE_ADMIN"})
public class AuthorizedController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthorizedController.class);

    @Autowired
    private VacationService vacationService;

    @Autowired
    private VacationRepository vacationRepository;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserDetailsRepository userDetailsRepository;

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
    @RequestMapping("/vacationList")
    public String vacationList(Model model, Authentication authentication) {
        UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();
        UUID userId = principal.getId();
        model.addAttribute("vacationList", vacationService.findUserVacation(userId));
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

        redirectAttributes.addFlashAttribute("validatedVacation", vacation);
        System.out.println(vacation);
        Integer daysToDeduct = vacation.countDays();

        if (daysToDeduct == 0) {
            return new RedirectView("/nothingToDeduct");
        } else {
            return new RedirectView("/summary");
        }
    }
    @RequestMapping ("/summary")
    public String summary(@ModelAttribute("validatedVacation") Vacation vacation,
                          Model model) {
        model.addAttribute("validatedVacation", vacation);
        return "summary-view";
    }

    @PostMapping("/summary")
    public String confirmVacation(@ModelAttribute Vacation vacation, Model model, Authentication authentication) {

        UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();
        CreateVacationDto createVacationDto = new CreateVacationDto(vacation.getDatesRange().getDateFrom(), vacation.getDatesRange().getDateTo(), principal.getId());
        vacationService.addNewVacation(createVacationDto);
        model.addAttribute("vacationList", vacationService.findUserVacation(principal.getId()));
        return "vacation-list";
    }

    @RequestMapping("/nothingToDeduct")
    public String nothingToDeduct() {
        return "nothing-to-deduct";
    }

    @RequestMapping("/deleteVacation")
    public RedirectView deleteVacation(@RequestParam UUID id) {
        VacationDAO vacation = vacationRepository.findById(id).get();
        if (vacation.getDateFrom().isBefore(LocalDate.now())) {
        } else {
            vacationService.remove(id);
        }
        return new RedirectView("/vacationList");
    }

    @GetMapping("/userDetails")
    public String userDetails(Model model, Authentication authentication) {
        UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();
        Optional<UserDetailsDAO> optionalUserDetails = userDetailsRepository.findUserDetailsDAOByUser_Id(principal.getId());
        if(optionalUserDetails.isPresent()) {
            model.addAttribute("userDetails", optionalUserDetails.get());
        }
        else model.addAttribute("userDetails", null);
        return "user-details";
    }

    @GetMapping("/createDetailsForm")
    public String createUserDetails(Model model) {
        model.addAttribute("userDetails", new UserDetails());
        return "create-details-form";
    }

    @PostMapping("/createDetailsForm")
    public String createDetailsForm(@RequestParam("file") MultipartFile file,
                                    @ModelAttribute @Valid UserDetails userDetails,
                                    BindingResult result,
                                    Authentication authentication) throws IOException {
        if(result.hasErrors()) {
            return "create-details-form";
        }
        UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        if (file.isEmpty()) {
            fileName = "default.png";
        }
        userDetails.setProfilePicture(fileName);

        CreateUserDetailsDto createUserDetailsDto = new CreateUserDetailsDto(
                userDetails.getFirstName(),
                userDetails.getLastName(),
                userDetails.getDepartament(),
                principal.getId(),
                userDetails.getProfilePicture());
        UserDetailsDto userDetailsDto = userDetailsService.createUserDetails(createUserDetailsDto);

        if(fileName != "default.png") {
            String uploadDir = "src/main/resources/static/images/";
            FileUploadUtil.saveFile(uploadDir, fileName, file);
        }
        return "user-details";
    }

    @GetMapping("/editDetails")
    public String editDetails(Model model, Authentication authentication) {
        UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();
        UUID userId = principal.getId();
        UserDetailsDAO userDetailsDAO = userDetailsRepository.findUserDetailsDAOByUser_Id(userId).get();
        UserDetails userDetails = new UserDetails();
        userDetails.setFirstName(userDetailsDAO.getFirstName());
        userDetails.setLastName(userDetailsDAO.getLastName());
        userDetails.setDepartament(userDetailsDAO.getDepartament());

        model.addAttribute("userDetails", userDetails);
        return "update-details-form";
    }

    @PostMapping("/editDetails")
    public String editDetailsForm(@RequestParam("file") MultipartFile file,
                                  @ModelAttribute @Valid UserDetails userDetails,
                                  BindingResult result,
                                  Authentication authentication) throws IOException {
        if(result.hasErrors()) {
            return "update-details-form";
        }
        UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();
        UUID userId = principal.getId();
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        if (file.isEmpty()) {
            fileName = userDetailsRepository.findUserDetailsDAOByUser_Id(userId).get().getProfilePicture();
        }
        userDetails.setProfilePicture(fileName);

        UserDetailsDto userDetailsDto = new UserDetailsDto(
                userDetails.getFirstName(),
                userDetails.getLastName(),
                userDetails.getDepartament(),
                userDetails.getProfilePicture());

        userDetailsService.updateUserDetails(userDetailsDto, userId);

        if(fileName != userDetailsRepository.findUserDetailsDAOByUser_Id(userId).get().getProfilePicture()) {
            String uploadDir = "src/main/resources/static/images/";
            FileUploadUtil.saveFile(uploadDir, fileName, file);
        }
        return "user-details";
    }
}
