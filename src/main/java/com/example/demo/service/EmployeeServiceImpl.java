package com.example.demo.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.customexception.BusinessException;
import com.example.demo.entity.Employee;
import com.example.demo.repos.EmployeeInterface;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeInterface employeeDAO;

	@Override
	public List<Employee> getEmployeeList() {
		return employeeDAO.findAll();
	}

	@Override
	public Employee addNewEmployee(Employee employee) {
		
		if(employee.getName().isBlank()) {
			throw new BusinessException("610","The employee name should not be empty");
		}
		try {
			return employeeDAO.save(employee);
		}
		catch(IllegalArgumentException e) {
			throw new BusinessException("611","There is something wrong"+e.getMessage());
		}
		catch (Exception e) {
			throw new BusinessException("612", "There is something wrong in service layer"+e.getMessage());
		}
	}

	@Override
	public Employee getEmployeeById(long empId) {
		return employeeDAO.findById(empId).get();
	}

	@Override
	public void removeEmployee(long id) {
		employeeDAO.deleteById(id);		
	}

	@Override
	public Employee updateEmployee(Employee employee) {
		return employeeDAO.save(employee);
	}
	
}
