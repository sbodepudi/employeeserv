package com.paypal.bfs.test.employeeserv.impl;

import com.paypal.bfs.test.employeeserv.api.EmployeeResource;
import com.paypal.bfs.test.employeeserv.api.model.Address;
import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.exception.EmployeeNotFoundException;
import com.paypal.bfs.test.employeeserv.service.EmployeeRepoService;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/**
 * Implementation class for employee resource.
 */
@RestController
public class EmployeeResourceImpl implements EmployeeResource {
	
	@Autowired
	EmployeeRepoService employeeRepoService;
	

    @Override
    public ResponseEntity<Employee> employeeGetById(@PathVariable("id") String id) throws EmployeeNotFoundException {
    	
    	try {
    		com.paypal.bfs.test.employeeserv.model.Employee employeeDB = employeeRepoService.getEmployee(id);
    	
    		Employee employee = null;
    	
    		employee = new Employee();
		 	employee.setId(employeeDB.getId());
	        employee.setFirstName(employeeDB.getFirstName());
	        employee.setLastName(employeeDB.getLastName());
	        employee.setDateOfBirth(
	        			Date.from(employeeDB.getDateOfBirth().atStartOfDay(ZoneId.systemDefault()).toInstant()));
	        
	        Address address =  new Address();
	        address.setLine1(employeeDB.getAddress().getLine1());
	        address.setLine2(employeeDB.getAddress().getLine2());
	        address.setCity(employeeDB.getAddress().getCity());
	        address.setState(employeeDB.getAddress().getState());
	        address.setZip(employeeDB.getAddress().getZip());
	        employee.setAddress(address);
	        return new ResponseEntity<>(employee, HttpStatus.OK);
    	}
	        catch (EmployeeNotFoundException exc) {
	             throw new ResponseStatusException(
	               HttpStatus.NOT_FOUND, "Employee Not Found", exc);
	        }
    	
        
        
    }
    
	@Override
	public ResponseEntity<Employee> createEmployee(Employee employee) {
		
		
		if(employee != null) {
			com.paypal.bfs.test.employeeserv.model.Employee employeeToSave= new com.paypal.bfs.test.employeeserv.model.Employee();
			 employeeToSave.setFirstName(employee.getFirstName());
			 employeeToSave.setLastName(employee.getLastName());
			 employeeToSave.setDateOfBirth(employee.getDateOfBirth().toInstant()
				      .atZone(ZoneId.systemDefault())
				      .toLocalDate());
			 
			 com.paypal.bfs.test.employeeserv.model.Address addressToSave = new com.paypal.bfs.test.employeeserv.model.Address();
			 
			 addressToSave.setCity(employee.getAddress().getCity());
			 addressToSave.setLine1(employee.getAddress().getLine1());
			 addressToSave.setLine2(employee.getAddress().getLine2());
			 addressToSave.setState(employee.getAddress().getState());
			 addressToSave.setZip(employee.getAddress().getZip());
			 
			 employeeToSave.setAddress(addressToSave);			 
			 
			 employeeToSave = employeeRepoService.createEmployee(employeeToSave);			 
			 employee.setId(employeeToSave.getId());			 
			
		}
		return new ResponseEntity<>(employee, HttpStatus.CREATED);
	}
}
