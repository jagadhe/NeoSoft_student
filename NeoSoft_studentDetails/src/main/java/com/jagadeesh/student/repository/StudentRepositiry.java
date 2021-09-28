package com.jagadeesh.student.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jagadeesh.student.bean.Student;

public interface StudentRepositiry extends JpaRepository<Student, Long> {

}
