package com.sid.project.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sid.project.entity.User;

public interface UserRepository extends JpaRepository<User, String> {

}
