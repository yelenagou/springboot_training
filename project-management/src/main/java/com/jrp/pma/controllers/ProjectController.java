package com.jrp.pma.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jrp.pma.dao.EmployeeRepository;
import com.jrp.pma.dao.ProjectRepository;
import com.jrp.pma.entities.Employee;
import com.jrp.pma.entities.Project;

@Controller
@RequestMapping("/projects")
public class ProjectController {
	@Autowired
	ProjectRepository projectRepository;
	@Autowired
	EmployeeRepository empRepo;
	@GetMapping
	public String listProjects(Model model) {
		List<Project> listProjects = projectRepository.findAll();
				
		model.addAttribute("projects", listProjects);
		return "projects/list-projects";
		
	}
	
	@GetMapping("/new")
	public String displayProjectForms(Model model) {
		Project aProject = new Project();
		List<Employee> employees = empRepo.findAll();
		model.addAttribute("project", aProject);
		model.addAttribute("allEmployees", employees);
		return "projects/new-prj";
		
	}
	
	@PostMapping("/save")
	public String createProject(Project project, @RequestParam List<Long> employees, Model model) {
		projectRepository.save(project);
		Iterable<Employee>chosenEmployees =  empRepo.findAllById(employees);
		for (Employee emp : chosenEmployees)
		{
			//emp.setTheProject(project);
			empRepo.save(emp);
		}
		// use a redirect to prevent duplicate submissions
		return "redirect:/projects";
		
	}
}
