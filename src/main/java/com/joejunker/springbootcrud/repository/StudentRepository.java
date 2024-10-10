package com.joejunker.springbootcrud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.joejunker.springbootcrud.model.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {
	
	
	 // Method to find students by their name
	// This is from the JPA repository 
	// basically executes a query like this 
	// SELECT * FROM student WHERE name = ?
    // Selects all student with that name 
    List<Student> findByName(String name); // Spring Data JPA will implement this method for you

}
