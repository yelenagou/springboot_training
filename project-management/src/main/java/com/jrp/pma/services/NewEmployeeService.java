package com.jrp.pma.services;

import org.springframework.stereotype.Service;

@Service
public class NewEmployeeService {

	IStaffRepository empRepo;
	
	public NewEmployeeService(IStaffRepository empRepo) {
		super();
		this.empRepo = empRepo;
	}
}
