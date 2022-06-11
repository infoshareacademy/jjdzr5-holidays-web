package org.isa.holidaysweb.web;


import lombok.SneakyThrows;
import org.isa.holidaysweb.exception.RecordException;
import org.isa.holidaysweb.model.EmployeeEntity;
import org.isa.holidaysweb.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class EmployeeMvcController {
	@Autowired
	EmployeeService service;

	@SneakyThrows
	@RequestMapping
	public String getAllEmployees(Model model) {
		List<EmployeeEntity> list = service.getAllEmployees();
		model.addAttribute("employees", list);
		if(list.size() > 0) {
			model.addAttribute("registry", LocalDateTime.now());
		}
		return "list-employees";
	}

	@RequestMapping(path = {"/edit", "/edit/{id}"})
	public String editEmployeeById(Model model, @PathVariable("id") Optional<Long> id) throws RecordException {
		if (id.isPresent()) {
			EmployeeEntity entity = service.getEmployeeById(id.get());
			model.addAttribute("employee", entity);
		} else {
			model.addAttribute("employee", new EmployeeEntity());
		}
		return "add-edit-employee";
	}
	
	@RequestMapping(path = "/delete/{id}")
	public String deleteEmployeeById(Model model, @PathVariable("id") Long id) throws RecordException {
		service.deleteEmployeeById(id);
		return "redirect:/";
	}

	@RequestMapping(path = "/createEmployee", method = RequestMethod.POST)
	public String createOrUpdateEmployee(EmployeeEntity employee, Model model) {
		service.createOrUpdateEmployee(employee);
		model.addAttribute("registry", LocalDateTime.now());
		return "redirect:/";
	}
}
