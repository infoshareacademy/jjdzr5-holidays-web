package org.isa.holidaysweb.web;

import lombok.SneakyThrows;
import org.isa.holidaysweb.exception.RecordException;
import org.isa.holidaysweb.model.EmployeeEntity;
import org.isa.holidaysweb.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class EmployeeMvcController {
	@Autowired
	EmployeeService service;

	@RequestMapping
	public String getAllEmployees(Model model) {
		List<EmployeeEntity> list = service.getAllEmployees();
		model.addAttribute("employees", list);
		return "list-employees";
	}

	@SneakyThrows
	@RequestMapping(path = {"/add-edit-employee", "/update-employee/{id}"}, method = {RequestMethod.GET, RequestMethod.POST})
	public String editEmployeeById(Model model, @PathVariable("id") Optional<Long> id) {
		if (id.isPresent()) {
			model.addAttribute("employee", service.getEmployeeById(id.get()));
		} else {
			model.addAttribute("employee", new EmployeeEntity());
		}
		return "add-edit-employee";
	}

	@RequestMapping(path = "/delete/{id}")
	public String deleteEmployeeById(@PathVariable("id") Long id) throws RecordException {
		service.deleteEmployeeById(id);
		return "redirect:/";
	}

	@PostMapping(value = {"/createEmployee"})
	@RequestMapping(path = {"/add-edit-employee", "/update-employee/{id}"}, method = {RequestMethod.POST})
	public String createOrUpdateEmployee(@Valid EmployeeEntity employee, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "add-edit-employee";
		}
		service.createOrUpdateEmployee(employee);
		model.addAttribute("employee", employee);
		return "redirect:/";
	}
}