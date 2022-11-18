package com.test.employee.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.employee.dao.EmployeeDao;
import com.test.employee.exceptions.EmployeeException;
import com.test.employee.model.Employee;

@Service
public class EmployeeService {

	public EmployeeService() {
		System.out.println("Inside Employee Controller");
	}
	@Autowired
	private EmployeeDao employeeDao;

	public Employee createEmployee(Employee employee)
	{
		Employee result = employeeDao.save(employee);
		return result;
	}

	public Employee readEmployeeById(Integer employeeId)
	{
		Optional<Employee> result= employeeDao.findById(employeeId);
		if(result.isPresent())
		{
			Employee employee = result.get();
			return employee;
		}
		return null;
	}

	public Employee updateEmployee(Employee employee, Integer employeeId) throws EmployeeException
	{
		Optional<Employee> optional = employeeDao.findById(employeeId);
		Employee updatedEmployee =  null;
		if(optional.isPresent())
		{
			Employee existingEmployee = optional.get();
			if(employee.getAddress() != null)
			{
				existingEmployee.setAddress(employee.getAddress());
			}
			if(employee.getContactNo() != null)
			{
				existingEmployee.setContactNo(employee.getContactNo());
			}
			if(employee.getDesignation() != null)
			{
				existingEmployee.setDesignation(employee.getDesignation());
			}
			if(employee.getSalary() != null)
			{
				existingEmployee.setSalary(employee.getSalary());
			}
			updatedEmployee = employeeDao.save(existingEmployee);
			return updatedEmployee;
		}
		else
		{
			throw new EmployeeException("Employee with Id:"+employeeId+ " doesn't exist");
		}
	}

	public void deleteEmployeeById(Integer employeeId) throws EmployeeException
	{
		Optional<Employee> optional = employeeDao.findById(employeeId);
		if(optional.isPresent())
		{
			employeeDao.deleteById(employeeId);
		}
		else
		{
			throw new EmployeeException("Employee with Id:"+employeeId+ " doesn't exist");
		}
	}


}
