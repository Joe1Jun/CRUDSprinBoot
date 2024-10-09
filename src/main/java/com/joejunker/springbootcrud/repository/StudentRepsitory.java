package com.joejunker.springbootcrud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.joejunker.springbootcrud.model.Student;

public interface StudentRepsitory extends JpaRepository<Student, Integer> {
	
	
	

}
