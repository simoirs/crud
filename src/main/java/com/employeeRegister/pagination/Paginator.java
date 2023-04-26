package com.employeeRegister.pagination;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.employeeRegister.model.Employee;
import com.employeeRegister.repository.EmployeeRepository;

public class Paginator {
	
	private EmployeeRepository employeeRepository;
	

	public Paginator(EmployeeRepository employeeRepository) {
		super();
		this.employeeRepository = employeeRepository;
	}


	public Page<Employee> findPaginated(int pageNo, int pageSize) {
		/*
		 * @param pageNo 
		 * 
		 * Viene passato come almeno 1, ma le pagine sono zero-based
		 * quindi meno uno.
		 */
		Pageable pageable = PageRequest.of(pageNo -1, pageSize);
		return employeeRepository.findAll(pageable);
	}
}
