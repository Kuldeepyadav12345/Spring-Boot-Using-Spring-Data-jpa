package com.training.employeeservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.training.employeeservice.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	public List<Employee> findByDepartment(String Department);

	public List<Employee> findBySalaryLessThan(String Department);

	public List<Employee> findBySalaryBetween(int low, int high);

	public List<Employee> findByName(String Pattern);

	@Query(value = "FROM Employee WHERE department=?1 AND salary=?2")
	public List<Employee> findEmployeess(String department, int salary);
}
