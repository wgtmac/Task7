
package edu.cmu.cs.webapp.task7.controller;


import javax.servlet.http.HttpServletRequest;

import edu.cmu.cs.webapp.task7.model.EmployeeDAO;
import edu.cmu.cs.webapp.task7.model.Model;


public class ViewCustomerAction extends Action {
	private EmployeeDAO employeeDAO;

	public ViewCustomerAction(Model model) {
		employeeDAO = model.getEmployeeDAO();
	}

	public String getName() {
		return "viewCustomer.do";
	}

	public String perform(HttpServletRequest request) {
		return "viewCustomer.jsp";
	}
}