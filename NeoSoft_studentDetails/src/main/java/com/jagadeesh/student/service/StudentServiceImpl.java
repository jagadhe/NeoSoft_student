package com.jagadeesh.student.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jagadeesh.student.bean.Student;
import com.jagadeesh.student.repository.StudentRepositiry;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepositiry stdRepo;

	@Override
	public Student addStudent(Student student) {
		return stdRepo.save(student);
	}

	@Override
	public List<Student> getAll() {
		return stdRepo.findAll();
	}

	@Override
	public Student getStudentById(Long id) {
	Student student = null;
		 Optional<Student> findById = stdRepo.findById(id);
		
		if (findById.isPresent()) { 
			 student = findById.get();
		}
		return student;

	}

}
