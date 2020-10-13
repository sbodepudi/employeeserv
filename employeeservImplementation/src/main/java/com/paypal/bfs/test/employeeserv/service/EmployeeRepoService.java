package com.paypal.bfs.test.employeeserv.service;

import com.paypal.bfs.test.employeeserv.exception.EmployeeNotFoundException;
import com.paypal.bfs.test.employeeserv.model.Employee;

public interface EmployeeRepoService {
	
	public Employee createEmployee(Employee emp);
    public Employee getEmployee(String empId) throws EmployeeNotFoundException;
}
