package com.studentManage.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.studentManage.exception.ResourceNotFoundException;
import com.studentManage.model.Student;
import com.studentManage.repository.StudentRepository;

@RestController
@RequestMapping("/api/auth")
public class StudentController {
	@Autowired
	private StudentRepository studentRepository;
	
	
	@CrossOrigin
	@PostMapping("/student")
	public Student createStudent(@RequestBody Student student) {
		Student student1 = studentRepository.save(student);
		return student1;
	}
	
	@CrossOrigin
	@GetMapping("/student")
	public Page<Student> listStudents(Pageable pageable){
		System.out.println(pageable);
		return studentRepository.findAll(pageable);
	}
	
	@GetMapping("/student/{id}")
	public Student getById(@PathVariable Long id) {
		Student student =  studentRepository.getOne(id);
		return student;
	}
	

	@CrossOrigin
	@DeleteMapping("/student/{id}")
	public ResponseEntity<?> deleteStudent(@PathVariable Long id) {
		Optional<Student>  student = studentRepository.findById(id);
		if (student.isPresent()) {
			studentRepository.delete(student.get());
			return ResponseEntity.ok().build();
		}
		else{
			new ResourceNotFoundException("Student not found with id "+id);
			return null;
		}
	}
	
}
