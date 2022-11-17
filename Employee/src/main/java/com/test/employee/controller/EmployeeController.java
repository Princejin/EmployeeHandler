package com.test.employee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.test.employee.exceptions.EmployeeException;
import com.test.employee.model.Employee;
import com.test.employee.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	public EmployeeController() {
		System.out.println("Inside Employee Controller");
	}
	@Autowired
	EmployeeService employeeService;

	
	@PostMapping(consumes = {"application/json"}, produces = {"application/json"}, path = "/createEmployee")
	public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee)
	{
		Employee resultEmployee = employeeService.createEmployee(employee);
		return new ResponseEntity<Employee>(resultEmployee, HttpStatus.CREATED);
	}

	@GetMapping(produces = {"application/json"}, path = "/readEmployee/{employeeId}")
	public ResponseEntity<?> getEmployee(@PathVariable Integer employeeId) throws EmployeeException
	{
		Employee result= employeeService.readEmployeeById(employeeId);
		if(result != null)
		{
			return new ResponseEntity<>(result, HttpStatus.OK);
		}
		throw new EmployeeException("Employee with Id: "+employeeId+ "doesn't exist");
	}

	@PutMapping(consumes = {"application/json"}, produces = {"application/json"}, path = "/updateEmployee/{employeeId}")
	public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee, @PathVariable Integer employeeId) throws EmployeeException
	{
		Employee resultEmployee = employeeService.updateEmployee(employee, employeeId);
		return new ResponseEntity<Employee>(resultEmployee, HttpStatus.OK);
	}

	@DeleteMapping(path = "/deleteEmployee/{employeeId}")
	public ResponseEntity deleteEmployee(@PathVariable  Integer employeeId)
	{
		employeeService.deleteEmployeeById(employeeId);
		return ResponseEntity.ok().build();
	}

}
