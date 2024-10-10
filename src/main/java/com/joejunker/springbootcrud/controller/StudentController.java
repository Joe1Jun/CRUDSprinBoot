package com.joejunker.springbootcrud.controller;// Package declaration for organizing controller classes.

import java.util.List;

// Importing necessary Spring annotations and classes.
import org.springframework.beans.factory.annotation.Autowired; // Used for dependency injection.
import org.springframework.web.bind.annotation.GetMapping; //use to handle HTTP get requests
import org.springframework.web.bind.annotation.PostMapping; // Used to handle HTTP POST requests.
import org.springframework.web.bind.annotation.RequestBody; // Used to bind the request body to a method parameter.
import org.springframework.web.bind.annotation.RequestMapping; // Used to map web requests to specific handler methods.
import org.springframework.web.bind.annotation.RestController; // Indicates that this class serves RESTful web services.

import com.joejunker.springbootcrud.model.Student;
import com.joejunker.springbootcrud.service.StudentService;

 // Importing the Student model, representing the student entity.
 // Importing the StudentService interface for business logic.

// The @RestController annotation indicates that this class is a controller where every method returns a domain object instead of a view.
// It combines @Controller and @ResponseBody, eliminating the need to annotate every method with @ResponseBody.
@RestController
@RequestMapping("/student") // This annotation specifies that any request to /student will be handled by this controller.

public class StudentController {

    // The @Autowired annotation allows Spring to automatically inject an instance of the StudentService.
    @Autowired
	private StudentService studentService; // Instance of StudentService to handle business logic related to students.
    
    // The @PostMapping annotation maps HTTP POST requests onto this method.
    @PostMapping("/add") // This method will handle POST requests to /student/add.
    public String add(@RequestBody Student student) {
    	// The @RequestBody annotation binds the HTTP request body to the Student object parameter.
    	// It allows the incoming JSON data to be automatically deserialized into a Student object.

    	studentService.save(student); // Calling the saveStudent method of the studentService to save the student in the database.
    	return "New Student is added"; // Returning a confirmation message as a response.
    }
    @GetMapping("/getAll")
    public List <Student> getAllStudents (){
    	
    	 return studentService.listAll();
    	
    }
}
