package com.sid.project.service;

import com.sid.project.entity.Appointment;
import com.sid.project.entity.AppointmentId;


public interface AppointmentService {
	
	public Appointment createAppointment(Appointment appointment);
	
	public Appointment findByAppointment(AppointmentId id);
}
