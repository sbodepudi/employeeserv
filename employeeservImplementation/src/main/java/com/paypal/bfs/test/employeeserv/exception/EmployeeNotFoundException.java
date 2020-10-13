package com.paypal.bfs.test.employeeserv.exception;

public class EmployeeNotFoundException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6198958053693571879L;
	public EmployeeNotFoundException() {
        super();
    }
    public EmployeeNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
    public EmployeeNotFoundException(String message) {
        super(message);
    }
    public EmployeeNotFoundException(Throwable cause) {
        super(cause);
    }
}
