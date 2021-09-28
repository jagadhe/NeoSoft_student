package com.jagadeesh.student.service;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jagadeesh.student.bean.Project;
import com.jagadeesh.student.bean.Role;
import com.jagadeesh.student.bean.Student;
import com.jagadeesh.student.repository.StudentRepositiry;

@SpringBootTest
public class ServiceImplTest {

	
	@Autowired
	private StudentRepositiry repo;
	
	
	@Test
	public void testAddStudent() {
		

		Set<Project> projects=new HashSet<>();
		Project Project1=Project.builder().projName("P1").duration(1).build();
		Project Project2=Project.builder().projName("P2").duration(10).build();
		Project Project3=Project.builder().projName("P3").duration(2).build();
	
		projects.add(Project1);
		projects.add(Project2);
		projects.add(Project3);
	
		
		Student student=Student.builder()
				.firstName("Jaga")
				.lastName("Deesh")
				.email("jagadeeshjai1995@gmail.com")
				.role("student")
				.projects(projects)
				.build();
		
		
		repo.save(student);
		}
}
