package com.sid.project.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sid.project.entity.SpaStylists;

public interface StylistRepo extends JpaRepository<SpaStylists, Integer> {

}
