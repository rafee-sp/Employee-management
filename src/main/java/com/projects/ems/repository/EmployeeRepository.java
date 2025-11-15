package com.projects.ems.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.projects.ems.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{
	
	@Query("SELECT e FROM Employee e WHERE e.employeeId = :searchTerm OR(CONCAT(e.firstName,' ',e.lastName)) LIKE %:searchTerm% OR e.cellphone LIKE %:searchTerm% OR e.dob LIKE %:searchTerm%")
	public Page<Employee> searchEmployees(@Param ("searchTerm")String searchTerm, Pageable pageable);

	public Employee getByEmail(String email);

	public Employee getByCellphone(String cellphone);

	@Query("SELECT e FROM Employee e WHERE e.email = :email AND e.id != :id")
	public Employee findByEmailAndIdNot(@Param ("email")String email,@Param ("id") Long id);

	@Query("SELECT e FROM Employee e WHERE e.cellphone = :cellphone AND e.id != :id")
	public Employee findByCellphoneNotAndId(String cellphone, long id);
	
	public Page<Employee> findAll(Pageable pageable);
	
}
