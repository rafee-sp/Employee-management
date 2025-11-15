package com.projects.ems.service;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.projects.ems.entity.Employee;

@Service
public interface EmployeeService {
	
//	public List<Employee> getAllEmployees();
	
	public  Page<Employee> getAllEmployees(Pageable pageable);

	public Employee getEmployee(Long id);

	public Map<String, Object> addEmployee(Employee employee);

	public void deleteEmployee(Long id);

	public Map<String, Object> updateEmployee(Employee employee);
	
	public Page<Employee> searchEmployees(String searchTerm, Pageable pageable);
	
	
}
