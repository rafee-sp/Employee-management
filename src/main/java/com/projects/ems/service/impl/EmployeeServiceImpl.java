package com.projects.ems.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.projects.ems.entity.Employee;
import com.projects.ems.repository.EmployeeRepository;
import com.projects.ems.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	
	
	private EmployeeRepository employeeRepo;
	
	private EmployeeServiceImpl(EmployeeRepository employeeRepo) {
		this.employeeRepo = employeeRepo;		
	}

	@Override
	public Page<Employee> getAllEmployees(Pageable pageable) {
					
		System.out.println( employeeRepo.findAll(pageable));
		return employeeRepo.findAll(pageable);
	}

	@Override
	public Employee getEmployee(Long id) {	
		
		Optional<Employee> employee = employeeRepo.findById(id);
		
		return employee.orElse(null);
	}

	@Override
	public Map<String, Object> addEmployee(Employee employee) {
		
		Map<String, Object> response = new HashMap<>();
		
		Employee existingByEmail = employeeRepo.getByEmail(employee.getEmail());
		
		if(existingByEmail != null) {
			
			response.put("success", false);
			response.put("responseMsg", "Employee with Email address already exists");
			return response;
		}
		
		Employee existingByCellphone = employeeRepo.getByCellphone(employee.getCellphone());
		
		if(existingByCellphone != null) {
			
			response.put("success", false);
			response.put("responseMsg", "Employee with Cellphone already exists");
			return response;
		}
		
		Employee createdEmployee = employeeRepo.save(employee);
		String employeeId = "EMP" + createdEmployee.getId();
		createdEmployee.setEmployeeId(employeeId);
		employeeRepo.save(createdEmployee);
		
		response.put("success", true);
		response.put("responseMsg", "Employee created successfully");
		return response;
	}

	@Override
	public void deleteEmployee(Long id) {
		employeeRepo.deleteById(id);
	}

	@Override
	public Map<String, Object> updateEmployee(Employee employee) {
		
	Map<String, Object> response = new HashMap<>();
		
		Employee existingByEmail = employeeRepo.findByEmailAndIdNot(employee.getEmail(), employee.getId());
		
		if(existingByEmail != null) {
			
			response.put("success", false);
			response.put("responseMsg", "Employee with Email address already exists");
			return response;
		}
		
		Employee existingByCellphone = employeeRepo.findByCellphoneNotAndId(employee.getCellphone(),employee.getId());
		
		if(existingByCellphone != null) {
			
			response.put("success", false);
			response.put("responseMsg", "Employee with Cellphone already exists");
			return response;
		}
		
		employeeRepo.save(employee);
		response.put("success", true);
		response.put("responseMsg", "Employee Updated successfully");
		return response;
	}

	@Override
	public Page<Employee> searchEmployees(String searchTerm, Pageable pageable) {
		
		 Page<Employee> employee = null;
		
		try {
			
			if(searchTerm.contains("/")) {
				
				SimpleDateFormat inputFormat = new SimpleDateFormat("MM/dd/yyyy");
	            SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");

	            Date date = inputFormat.parse(searchTerm);
	            searchTerm =  outputFormat.format(date);
			}
			
			 employee = employeeRepo.searchEmployees(searchTerm, pageable);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
				
		return employee;
	}
	
	/*
	@Override
	public List<Employee> searchEmployeeByCellphone(String cellphone) {
		
		List<Employee> employee = employeeRepo.findByCellphoneContaining(cellphone);
		
		return employee;
	}
	*/

}
