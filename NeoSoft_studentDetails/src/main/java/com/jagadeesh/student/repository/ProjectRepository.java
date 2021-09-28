package com.jagadeesh.student.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jagadeesh.student.bean.Project;

public interface ProjectRepository extends JpaRepository<Project,Long> {

}
