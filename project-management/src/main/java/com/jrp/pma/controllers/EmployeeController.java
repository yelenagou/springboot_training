package com.jrp.pma.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jrp.pma.dao.EmployeeRepository;
import com.jrp.pma.entities.Employee;


@Controller
@RequestMapping("/employees")
public class EmployeeController {
	@Autowired
	EmployeeRepository emplRep;
	@GetMapping
	public String listEmployees(Model model) {
		
		List<Employee> listEmpl = emplRep.findAll();
		
		model.addAttribute("employees", listEmpl);
		return "employees/list-employees";
		
	}
	@GetMapping("/new")
	public String displayEmployeeForms(Model model) {
		
		Employee anEmpl = new Employee();
		
		model.addAttribute("employee", anEmpl);
		return "employees/new-employee";
		
	}
	
	@PostMapping("/save")
	public String createEmployee(Employee employee, Model model) {
		//save to db using employee crud repository
		emplRep.save(employee);
		// use a redirect to prevent duplicate submissions
		return "redirect:/employees";
		
	}
}
