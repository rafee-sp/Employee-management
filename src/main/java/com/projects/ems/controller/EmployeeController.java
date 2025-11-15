package com.projects.ems.controller;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.projects.ems.entity.Employee;
import com.projects.ems.service.EmployeeService;


@RestController
public class EmployeeController {
	
	private EmployeeService employeeService;
	
	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@GetMapping("/")
	public String helloWorld() {
		
		return "Hello World..!";
		
	}      
	
	/*
	@GetMapping("/employees")
	public List<Employee> GetAllEmployees() {
		System.out.println("get employees called");
		return employeeService.GetAllEmployees();
		
	}
	
	
	@GetMapping("/employees")
	public ResponseEntity< List<Employee>> getAllEmployees() {

		List<Employee> employees = employeeService.getAllEmployees();
		return new ResponseEntity<>(employees,HttpStatus.OK);
		
	}
	
 */
	
	
	@GetMapping("/employee")
	public ResponseEntity< Page<Employee>> getAllEmployees(Pageable pageable) {
		
		Page<Employee> employees = employeeService.getAllEmployees(pageable);
		return new ResponseEntity<>(employees,HttpStatus.OK);
		
	}
	
	@GetMapping("/employee/{id}")
	public Employee getEmployee(@PathVariable Long id) {
		Employee employee = employeeService.getEmployee(id);
			
		return employee;	
	}
	
	@PostMapping("/employee")
	public ResponseEntity<Map<String, Object>> addEmployee(@RequestBody Employee employee) {

		 Map<String, Object> response = employeeService.addEmployee(employee);
	       
		 return ResponseEntity.ok(response);
	}
	
	@PutMapping("/employee/{id}")
	public ResponseEntity<Map<String, Object>> updateEmployee(@RequestBody Employee employee) {

		 Map<String, Object> response = employeeService.updateEmployee(employee);
	       
		 return ResponseEntity.ok(response);	
	}

	
	/*
	
	@PutMapping("/employee/{id}")
	public Employee updateEmployee(@RequestBody Employee employee) {
		System.out.println(employee);
		return employeeService.UpdateEmployee(employee);	
	}
*/

	
	@DeleteMapping("/employee/{id}")
	public void deleteEmployee(@PathVariable Long id) {
			
		employeeService.deleteEmployee(id);	
	}
	
	@GetMapping("/employee/search")
	public ResponseEntity< Page<Employee>> searchEmployee(@RequestParam String searchTerm, Pageable pageable) {
		
		System.out.println(searchTerm);
	//	List<Employee> fetchedEmployees = employeeService.searchEmployeeByName(name);
		Page<Employee> fetchedEmployees = employeeService.searchEmployees(searchTerm, pageable);
			
		return new ResponseEntity<>(fetchedEmployees,HttpStatus.OK);
		
	}
	
}
