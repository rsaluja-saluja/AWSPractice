package com.example.demo.repo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.example.demo.entity.Employee;

@Repository
public class EmployeeRepository {

	@Autowired
	private DynamoDBMapper dynamoDBMapper;
	
	public Employee save(Employee employee) {
		dynamoDBMapper.save(employee);
		return employee;
	}
	
	public Employee getEmployeeById(String id) {
		// return dynamoDBMapper.load(Employee.class, id); //This also works
		
		Employee emp= new Employee();

		emp.setId(id);
		DynamoDBQueryExpression<Employee> queryExpression = new DynamoDBQueryExpression<Employee>()
		    .withHashKeyValues(emp);

		List<Employee> itemList = dynamoDBMapper.query(Employee.class, queryExpression);
		System.out.println("size: "+itemList.size());
		for (int i = 0; i < itemList.size(); i++) {
		    System.out.println(itemList.get(i).getFirstName());
		    System.out.println(itemList.get(i).getLastName());		    
		}
		return itemList.get(0);
	}
	
	public String deleteEmployee(String id) {
		Employee emp = dynamoDBMapper.load(Employee.class, id);
		dynamoDBMapper.delete(emp);
		return "Employee deleted";		
	}
	
	public String update(String id, Employee emp) {
		System.out.println("ID: "+id);
		System.out.println("EMP: "+emp.getFirstName());
		dynamoDBMapper.save(emp, new DynamoDBSaveExpression()
				.withExpectedEntry("id", new ExpectedAttributeValue(new AttributeValue().withS(id))));
		return id;
	}
}
