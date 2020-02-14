package com.jrp.pma.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jrp.pma.dao.EmployeeRepository;

@Service
public class EmployeeService {
	
	 @Autowired 
	 IStaffRepository empRepoInter; //Field Injection
	  
	 @Autowired EmployeeRepository empRepo;
	  
	 //Setter injection 
	  EmployeeRepository empRepoSetter;
	  
	  @Autowired public void setEmpRepo(EmployeeRepository empRepoSetter) {
	  this.empRepoSetter = empRepoSetter; }
	  
	  //Constructor Injection 
	  EmployeeRepository empRepoConstr; public
	  EmployeeService(EmployeeRepository empRepoConstr) { super();
	  this.empRepoConstr = empRepoConstr; }
	 

}
