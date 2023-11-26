package com.sid.project.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sid.project.entity.SpaCategory;

public interface CategoryRepository extends JpaRepository<SpaCategory, String> {

}
