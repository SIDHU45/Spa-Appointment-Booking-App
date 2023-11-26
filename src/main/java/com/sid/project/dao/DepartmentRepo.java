package com.sid.project.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sid.project.entity.SpaDepartment;

public interface DepartmentRepo extends JpaRepository<SpaDepartment, Integer> {

}
