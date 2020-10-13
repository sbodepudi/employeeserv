/**
 * 
 */
package com.paypal.bfs.test.employeeserv.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paypal.bfs.test.employeeserv.exception.EmployeeNotFoundException;
import com.paypal.bfs.test.employeeserv.model.Employee;
import com.paypal.bfs.test.employeeserv.repo.EmployeeRepository;

/**
 * @author Sreenivas
 *
 */
@Service
public class EmployeeRepoServiceImpl implements EmployeeRepoService {
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Override
	public Employee createEmployee(Employee emp) {
		 return employeeRepository.save(emp);
	}

	@Override
	public Employee getEmployee(String empId) throws EmployeeNotFoundException{
		
		Optional<Employee> optionalEmp = employeeRepository.findById(Integer.valueOf(empId));
        if (optionalEmp.isPresent()) {
        	return optionalEmp.get();
        }else {
        	System.out.println("EmployeeNotFoundException "+empId);
        	throw new EmployeeNotFoundException(String.valueOf(empId));
        }
        
	}

}
