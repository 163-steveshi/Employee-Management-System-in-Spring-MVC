package com.luv2code.springboot.thymeleafdemo.controller;

import com.luv2code.springboot.thymeleafdemo.entity.Employee;
import com.luv2code.springboot.thymeleafdemo.service.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

	private EmployeeService employeeService;

	//use constructor injection
	@Autowired
	public EmployeeController(EmployeeService theEmployeeService){

		employeeService = theEmployeeService;
	}






	// add mapping for "/list"

	@GetMapping("/list")
	public String listEmployees(Model theModel) {

		List<Employee> theEmployees = employeeService.findAll();
		// add to the spring model
		theModel.addAttribute("employees", theEmployees);

		return "employees/list-employees";
	}
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel){

		//create model attribute to bind form data
		Employee theEmployee = new Employee();
		theModel.addAttribute("employee",theEmployee);
		//return the template that is used.
		return "employees/employee-form";
	}

	@GetMapping("/showFormForUpdate")
	//@requestParam use "@{/employees/showFormForUpdate(employeeId=${tempEmployee.id})}
	//to get the Id
	public String showFormForUpdate(@RequestParam("employeeId") int theId, Model theModel){

		//get the employee from the service
		Employee theEmployee = employeeService.findById(theId);
		//set employee in the model to prepopulate the form
		theModel.addAttribute("employee", theEmployee);
		//send over to our form
		return "employees/employee-form";

	}


	@GetMapping("/delete")
	public String delete(@RequestParam("employeeId") int theId){

		//delete the employee from the service
		employeeService.deleteById(theId);
		//return to the employee list page for reload and refresh
		return "redirect:/employees/list";

	}
	@PostMapping("/save")
	//use @modelattribute for data binding
	//it will seach employee in html template
	//and convert it to an Employee Objec
	public String saveEmployee(@ModelAttribute("employee") Employee theEmployee){

		//save the employee
		employeeService.save(theEmployee);
		//use a redirect prevent duplicate submission

		return "redirect:/employees/list";
	}
}









