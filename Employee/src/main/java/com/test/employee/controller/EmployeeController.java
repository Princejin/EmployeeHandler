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

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	public EmployeeController() {
		System.out.println("Inside Employee Controller");
	}
	@Autowired
	EmployeeService employeeService;

	@ApiResponses(value= {
			@ApiResponse(responseCode = "201", description = "Creates new employee in the System", content = {
					@Content(mediaType = "application/json", schema= @Schema(implementation = Employee.class))
			})
	})
	@PostMapping(consumes = {"application/json"}, produces = {"application/json"}, path = "/createEmployee")
	public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee)
	{
		Employee resultEmployee = employeeService.createEmployee(employee);
		return new ResponseEntity<Employee>(resultEmployee, HttpStatus.CREATED);
	}

	@ApiResponses(value= {
			@ApiResponse(responseCode = "200", description = "Retrive existing employee from the System based on employee id provided", content = {
					@Content(mediaType = "application/json", schema= @Schema(implementation = Employee.class))
			}),
			@ApiResponse(responseCode = "400", description = "Employee with provided id doesnot exist")
	})
	@GetMapping(produces = {"application/json"}, path = "/readEmployee/{employeeId}")
	public ResponseEntity<?> getEmployee(@PathVariable Integer employeeId) throws EmployeeException
	{
		Employee result= employeeService.readEmployeeById(employeeId);
		if(result != null)
		{
			return new ResponseEntity<>(result, HttpStatus.OK);
		}
		throw new EmployeeException("Employee with Id:"+employeeId+ " doesn't exist");
	}

	@ApiResponses(value= {
			@ApiResponse(responseCode = "200", description = "Update existing employee attributes present in system", content = {
					@Content(mediaType = "application/json", schema= @Schema(implementation = Employee.class))
			}),
			@ApiResponse(responseCode = "400", description = "Employee with provided id doesnot exist")
	})
	@PutMapping(consumes = {"application/json"}, produces = {"application/json"}, path = "/updateEmployee/{employeeId}")
	public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee, @PathVariable Integer employeeId) throws EmployeeException
	{
		Employee resultEmployee = employeeService.updateEmployee(employee, employeeId);
		return new ResponseEntity<Employee>(resultEmployee, HttpStatus.OK);
	}

	@ApiResponses(value= {
			@ApiResponse(responseCode = "200", description = "Deletes the employee with provided Id"),
			@ApiResponse(responseCode = "400", description = "Employee with provided id doesnot exist")
	})
	@DeleteMapping(path = "/deleteEmployee/{employeeId}")
	public ResponseEntity deleteEmployee(@PathVariable  Integer employeeId) throws EmployeeException
	{
		employeeService.deleteEmployeeById(employeeId);
		return ResponseEntity.ok().build();
	}

}
