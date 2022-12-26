package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Employee;

public interface EmployeeService {

	List<Employee> getEmployeeList();

	Employee addNewEmployee(Employee employee);

	Employee getEmployeeById(long empId);

	void removeEmployee(long id);

	Employee updateEmployee(Employee employee);
	
}
