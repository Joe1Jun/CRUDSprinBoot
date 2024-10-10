package com.joejunker.springbootcrud.controller;// Package declaration for organizing controller classes.

import java.util.List;
import java.util.NoSuchElementException;

// Importing necessary Spring annotations and classes.
import org.springframework.beans.factory.annotation.Autowired; // Used for dependency injection.
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping; //use to handle HTTP get requests
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping; // Used to handle HTTP POST requests.
import org.springframework.web.bind.annotation.PutMapping;
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
    
 // This method is mapped to GET requests sent to /student/{id}.
 // The {id} in the URL represents a path variable (dynamic value) that will be used to look up a student by ID.
 @GetMapping("/{id}")
 public ResponseEntity<Student> get(@PathVariable Integer id) {
     try {
         // Attempt to fetch the student from the service layer using the provided id.
         // This corresponds to a database lookup, which can either return a Student object or fail if the ID doesn't exist.
         Student student = studentService.get(id);
         
         // If the student with the given ID is found, return the Student object inside a ResponseEntity
         // along with an HTTP status code of 200 OK, indicating success.
         return new ResponseEntity<Student>(student, HttpStatus.OK);
         
     } catch (NoSuchElementException e) {
         // If no student with the provided ID is found, this exception is caught.
         // Respond with a 404 Not Found status, indicating that the resource (student) could not be located.
         return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
     }
 }
 
//This method is mapped to PUT requests sent to /student/{id}.
//The {id} in the URL represents a path variable that indicates the ID of the student to be updated.
@PutMapping("/{id}")
public ResponseEntity<Student> update(@RequestBody Student student, @PathVariable Integer id) {
  try {
      // Attempt to fetch the existing student from the service layer using the provided ID.
      // This corresponds to a database lookup, which can either return a Student object or throw an exception if the ID doesn't exist.
      Student existingStudent = studentService.get(id);
      
      // Here we assume that the student object received in the request body contains updated values.
      // We then call the save method on the studentService to persist the updated student information in the database.
      // It's important to ensure that the student object has the same ID as the existing student to maintain integrity.
      studentService.save(student);
      
      // If the update is successful, we return a ResponseEntity with an HTTP status code of 200 OK.
      // This indicates that the request has been successfully processed and the student has been updated.
      return new ResponseEntity<Student>(HttpStatus.OK);
      
  } catch (NoSuchElementException e) {
      // If no student with the provided ID is found, this exception is caught.
      // We respond with a 404 Not Found status, indicating that the resource (student) could not be located.
      return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
  }
}

//This method is mapped to DELETE requests sent to /student/{id}.
//The {id} in the URL represents a path variable that indicates the ID of the student to be deleted.
@DeleteMapping("/{id}")
public String delete(@PathVariable Integer id) {
 // Call the delete method of the studentService to remove the student with the specified ID from the database.
 // The studentService is responsible for handling the business logic and interaction with the database.
 studentService.delete(id);
 
 // Return a confirmation message indicating that the student with the specified ID has been deleted.
 return "Deleted Student with id " + id;
}

//New method to get all students by name
@GetMapping("/name/{name}") // Using name as a path variable
public ResponseEntity<List<Student>> getByName(@PathVariable String name) {
    List<Student> students = studentService.getAllByName(name); // Call the service method
    if (students.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Return NOT_FOUND if no students found
        
    }
    
    return new ResponseEntity<>(students, HttpStatus.OK); // Return the list of students with OK status
}
}