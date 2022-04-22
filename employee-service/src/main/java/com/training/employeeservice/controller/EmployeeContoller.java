package com.training.employeeservice.controller;

import java.net.http.HttpHeaders;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.training.employeeservice.exception.EmployeeNotFoundException;
import com.training.employeeservice.model.Employee;
import com.training.employeeservice.repository.EmployeeRepository;

import net.bytebuddy.dynamic.DynamicType.Builder.FieldDefinition.Optional;

@RestController

@RequestMapping("/api/v1")
//@CrossOrigin(origins={"http://www.myapp.com","http://wwe.com"})
//@CrossOrigin(origins="*")
public class EmployeeContoller {
	@Autowired
	private EmployeeRepository employeeRepository;

	@GetMapping("/employee")
	public ResponseEntity<List<Employee>> getAllEmpDetails() {
		List<Employee> employee = employeeRepository.findAll();
		org.springframework.http.HttpHeaders httHeaders = new org.springframework.http.HttpHeaders();
		httHeaders.add("Cookie", "Secret");
		httHeaders.add("Token", "asdasd");
		httHeaders.add("timestamp", LocalTime.now().toString());
		return new ResponseEntity<List<Employee>>(employee, httHeaders, HttpStatus.OK);
	}

	@GetMapping("/employee/{id}")
	public ResponseEntity<Employee> getEmployeeDetailsById(@PathVariable int id) {
		System.out.println(id);
		// Employee employee = employeeRepository.findById(id).get(); /// if the
		// employee doesn't exist then exception must
		/// be thrown with the help of exception handling
		Employee employee = employeeRepository.findById(id)
				.orElseThrow(() -> new EmployeeNotFoundException("Employee record does not exist"));
		return new ResponseEntity<Employee>(employee, HttpStatus.OK);

	}

	@DeleteMapping("/employee/{id}")
	public ResponseEntity<?> deleteEmployeeById(@PathVariable int id) {
		System.out.println(id);// if the employee dosent exist then an exception must be thrown
		// employeeRepository.deleteById(id);
		Employee employee = employeeRepository.findById(id)
				.orElseThrow(() -> new EmployeeNotFoundException("Employee not found "));
		/// If employee exist the delete it
		employeeRepository.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/employee/department/{department}")
	public ResponseEntity<List<Employee>> getEmpDetailsByDepartment(@PathVariable String Department) {
		List<Employee> employees = employeeRepository.findByDepartment(Department);
		return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
	}

	@GetMapping("/employee/department/{department}/{amount}")
	public ResponseEntity<List<Employee>> getEmpDetailsByDepAndSalary(@PathVariable String Department,
			@PathVariable int amount) {
		List<Employee> employees = employeeRepository.findEmployeess(Department, amount);
		return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
	}

	@PostMapping("/employees")
	public ResponseEntity<Employee> addNewEmployee(@RequestBody Employee employee) {
		Employee saveEmployee = employeeRepository.saveAndFlush(employee); // used for both update and insert operation
		System.out.println(employee);
		System.out.println(employee.getDepartment() + "" + employee.getName() + "" + employee.getSalary());
		// employee id will be automatically generated
		// The save method will take the object from Http request it will generate a
		// unique id insert the data into the database
		// and will return the same response from the database with unique employee id
		return new ResponseEntity<Employee>(saveEmployee, HttpStatus.CREATED);
	}

	@PutMapping("/employee/{id}")
	public ResponseEntity<Employee> UptadeEmployeeById(@RequestBody Employee employee) // here we are passing the while
	// object
	// because employee may also update the salary hence whole request body must be
	// taken into consideration
	{
		System.out.println(employee);
		Employee updatedEmployee = employeeRepository.save(employee);
		return new ResponseEntity<Employee>(updatedEmployee, HttpStatus.OK);
	}

	/*Writing this method in GlobalExceptionHandler
	 * @ExceptionHandler(EmployeeNotFoundException.class)
	public ResponseEntity<?> handleEmployeeNotFoundException(EmployeeNotFoundException ex) {
		return new ResponseEntity<String>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}*/

}
