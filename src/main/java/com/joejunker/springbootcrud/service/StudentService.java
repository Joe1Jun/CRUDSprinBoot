package com.joejunker.springbootcrud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.joejunker.springbootcrud.model.Student;
import com.joejunker.springbootcrud.repository.StudentRepository;

@Service
public class StudentService {
	
	
	
	@Autowired
	private StudentRepository studentRepository;
	
	public List<Student> listAll (){
		
		// Because the interface in StudentRepository uses the JPA interface 
		// it can use all the methods from the interface including the one below.
		return studentRepository.findAll();
	}
	
	public void save(Student student) {
		studentRepository.save(student);
	}
	
	public Student get(Integer Id) {
		
		return studentRepository.findById(Id).get();
	}
	
	public void delete(Integer Id) {
		studentRepository.deleteById(Id);
	}
}
