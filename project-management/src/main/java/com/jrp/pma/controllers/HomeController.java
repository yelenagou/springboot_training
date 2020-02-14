package com.jrp.pma.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jrp.pma.dao.EmployeeRepository;
import com.jrp.pma.dao.ProjectRepository;
import com.jrp.pma.dto.EmployeeProject;
import com.jrp.pma.dto.ProjectStatus;
import com.jrp.pma.entities.Employee;
import com.jrp.pma.entities.Project;
import com.jrp.pma.springExample.Car;
import com.yg.utils.DataCleansingService;

@Controller
public class HomeController {
	
	@Value("${version}")
	private String version;
	
	@Autowired
	Car car;
	@Autowired
	DataCleansingService dataCleanser;
	@Autowired
	ProjectRepository proRepo;
	@Autowired
	EmployeeRepository empRepo;
	@GetMapping("/")
	public String displayHome(Model model) throws JsonProcessingException {
		model.addAttribute("versionNumber", version);
		Map<String, Object> map = new HashMap<>();
		//queriying database for Projects
		List<Project> listOfProjects = proRepo.findAll();
		model.addAttribute("projects",listOfProjects);
		
		//qyeriying database for Employees
		List<EmployeeProject> listOfEmployeesProjectCount = empRepo.employeeProjects();
		model.addAttribute("employeeListProjectCount",listOfEmployeesProjectCount);
		
		List<ProjectStatus> projectStatus = proRepo.GetProjectStatusCount();
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonString = objectMapper.writeValueAsString(projectStatus);
		model.addAttribute("projectStatusCount", jsonString);
		return "main/home";
	}
}
