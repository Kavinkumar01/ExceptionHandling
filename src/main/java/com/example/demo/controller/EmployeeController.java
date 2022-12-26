package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.customexception.BusinessException;
import com.example.demo.customexception.ControllerException;
import com.example.demo.entity.Employee;
import com.example.demo.service.EmployeeService;

@RestController
@RequestMapping("/code")
public class EmployeeController {
	
	@Autowired
	EmployeeService emp;
	
	@GetMapping("/get")
	public ResponseEntity<List<Employee>> getEmployeeList(){
		List<Employee> empList= new ArrayList<>();
		empList.add(new Employee("kavin"));
		empList.add(new Employee("kumar"));
		return new ResponseEntity<List<Employee>>(emp.getEmployeeList(), HttpStatus.OK);
	}
	
	@PostMapping("/post")
	public ResponseEntity<?> saveEmployee(@RequestBody Employee employee) {
		try {
			Employee employeeSaved = emp.addNewEmployee(employee);
			return new ResponseEntity<String>("The Employee details of "+employeeSaved.getName()+" was saved successfully", HttpStatus.CREATED);
		}catch (BusinessException e) {
			ControllerException ce = new ControllerException(e.getErrorCode(),e.getErrorMessage());
			return new ResponseEntity<String>(ce.getErrorMessage(),HttpStatus.BAD_REQUEST);
		}catch (Exception e) {
			ControllerException ce = new ControllerException("614","Something went wrong in controller");
			return new ResponseEntity<ControllerException>(ce,HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") long empId){
		Employee employeewithId = emp.getEmployeeById(empId);
		return new ResponseEntity<Employee>(employeewithId, HttpStatus.OK);
	}
	
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deleteEmployee(@PathVariable long id){
		emp.removeEmployee(id);
		return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/update")
	public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee){
		Employee employeeUpdated= emp.updateEmployee(employee);
		return new ResponseEntity<Employee>(employeeUpdated, HttpStatus.ACCEPTED);
	}
	
}
