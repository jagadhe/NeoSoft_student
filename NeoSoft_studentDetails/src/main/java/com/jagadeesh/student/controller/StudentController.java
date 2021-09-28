package com.jagadeesh.student.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jagadeesh.student.bean.Student;
import com.jagadeesh.student.service.StudentService;

@RestController
@RequestMapping("/student")
public class StudentController {
	
	@Autowired
	private StudentService service;
	
	@PostMapping("/save")
	public ResponseEntity<?> saveStudent(Student student){
		Student addStudent = service.addStudent(student);
		return new ResponseEntity<>(addStudent, HttpStatus.CREATED);
		
	}
	@GetMapping("/getAll")
	public ResponseEntity<?> getAllStudent(){
		 List<Student> students = service.getAll();
		return new ResponseEntity<>(students, HttpStatus.FOUND);
		
	}
	
	@GetMapping("getById/{id}")
	public ResponseEntity<?> getStudentById(@PathVariable Long id){
		Student student = service.getStudentById(id);
		return new ResponseEntity<>(student, HttpStatus.FOUND);
	}
}
