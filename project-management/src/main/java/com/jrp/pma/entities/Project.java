package com.jrp.pma.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.JoinColumn;
@Entity
public class Project {
	public Project() {}
	public Project(String name, String stage, String description) {
		super();
		//this.projectId = projectId;
		this.name = name;
		this.stage = stage;
		this.description = description;
	}
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="project_seq")
	@SequenceGenerator(name="project_seq",sequenceName="project_seq", allocationSize=50)
	private long projectId;
	private String name;
	
	private String stage; //NOTSTARTED, COMPLETED, INPROGRESS
	private String description;
	
	@ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST},
			fetch=FetchType.LAZY)
	@JoinTable(name="project_employee",
		joinColumns=@JoinColumn(name="project_id"),
		inverseJoinColumns = @JoinColumn(name="employee_id"))
	private List<Employee> employees;
	
	
	public List<Employee> getEmployees() {
		return employees;
	}
	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStage() {
		return stage;
	}
	public void setStage(String stage) {
		this.stage = stage;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
