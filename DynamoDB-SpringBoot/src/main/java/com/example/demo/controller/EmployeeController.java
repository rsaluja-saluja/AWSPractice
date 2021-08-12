package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Employee;
import com.example.demo.repo.EmployeeRepository;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeRepository empRepo;
	
	@PostMapping("employee")
	public Employee saveEmployee(@RequestBody Employee emp) {
		return empRepo.save(emp);
	}
	
	@GetMapping("/employee/{id}")
	public Employee getEmployeeWithId(@PathVariable("id") String empId) {
		return empRepo.getEmployeeById(empId);
	}
	
	@DeleteMapping("/employee/{id}")
	public String deleteEmployee(@PathVariable("id") String empId) {
		return empRepo.deleteEmployee(empId);
	}
	
	@PutMapping("/employee/{id}")
	public String updateEmployee(@RequestBody Employee emp, @PathVariable("id") String empId) {
		return empRepo.update(empId, emp);
	}
}
