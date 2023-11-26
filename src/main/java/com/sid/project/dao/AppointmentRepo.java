package com.sid.project.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sid.project.entity.Appointment;
import com.sid.project.entity.AppointmentId;
public interface AppointmentRepo extends JpaRepository<Appointment, AppointmentId> {

}
