package com.jagadeesh.student.service;

import java.util.List;

import com.jagadeesh.student.bean.Student;

public interface StudentService {
	
	public Student addStudent(Student student);
	
	public List<Student> getAll();
	


	public Student getStudentById(Long id);

}
