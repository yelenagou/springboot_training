package com.jrp.pma.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;


@Entity
public class Employee {
	
	public Employee() {};
	public Employee(String firstName, String lastName, String email) {
		super();
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="employee_seq")
	//@SequenceGenerator(name = "employee_generator",sequenceName = "employee_seq", allocationSize = 10)
	@SequenceGenerator(name="employee_seq",sequenceName="employee_seq", allocationSize=10)
	private long employeeId;
	
	private String firstName;
	private String lastName;
	private String email;
	
	@ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST},
			fetch=FetchType.LAZY)
	@JoinTable(name="project_employee",
	joinColumns=@JoinColumn(name="employee_id"),
			inverseJoinColumns = @JoinColumn(name="project_id"))
	private List<Project> theProjects;
	
	
	
	public List<Project> getTheProjects() {
		return theProjects;
	}
	public void setTheProjects(List<Project> theProjects) {
		this.theProjects = theProjects;
	}
	public long getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
