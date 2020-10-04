package com.studentManage.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.studentManage.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long>{
	
}