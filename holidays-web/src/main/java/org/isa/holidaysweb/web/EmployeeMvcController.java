package org.isa.holidaysweb.web;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.isa.holidaysweb.exception.RecordException;
import org.isa.holidaysweb.model.EmployeeEntity;
import org.isa.holidaysweb.repository.EmployeeRepository;
import org.isa.holidaysweb.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Secured(value = {"ROLE_ADMIN"})
public class EmployeeMvcController {
	@Autowired
	EmployeeService service;

	//TODO: check
	@RequestMapping("/welcome_admin")
	@GetMapping(value = {"/list-employees"})
	public String getAllEmployees(Model model) {
		List<EmployeeEntity> list = service.getAllEmployees();
		model.addAttribute("employees", list);
		return "list-employees";
	}


	@SneakyThrows
	@RequestMapping(path = {"/add-edit-employee", "/update-employee/{id}"})
	public String editEmployeeById(Model model, @PathVariable("id") Optional<Long> id) {
		if (id.isPresent()) {
			model.addAttribute("employee", service.getEmployeeById(id.get()));
		} else {
			model.addAttribute("employee", new EmployeeEntity());
		}
		return "add-edit-employee";
	}

	//TODO: check
	@RequestMapping(path = "/delete/{id}")
	public String deleteEmployeeById(@PathVariable("id") Long id) throws RecordException {
		service.deleteEmployeeById(id);
		return "redirect:/";
	}
	@GetMapping(value = { "/add"})
	public String addEmployee(@ModelAttribute EmployeeEntity employee) {
		return "add-edit-employee";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addEmployee(@Valid @ModelAttribute("employee") EmployeeEntity employee, BindingResult bindingResult, Model model) {
		model.addAttribute("employee", employee);
		if (bindingResult.hasErrors()) {
			return "add-edit-employee";
		}
		service.createOrUpdateEmployee(employee);
		return "redirect:/";
	}
}