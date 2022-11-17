package com.test.employee.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.employee.model.Employee;

@Repository
public interface EmployeeDao extends JpaRepository<Employee, Integer>{
}
