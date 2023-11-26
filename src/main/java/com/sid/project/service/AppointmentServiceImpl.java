package com.sid.project.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sid.project.dao.AppointmentRepo;
import com.sid.project.entity.Appointment;
import com.sid.project.entity.AppointmentId;


@Service
public class AppointmentServiceImpl implements AppointmentService {
	
	private AppointmentRepo appointmentRepo;
	
	public AppointmentServiceImpl(AppointmentRepo theAppointmentRepo) {
		appointmentRepo = theAppointmentRepo;
	}

	@Override
	public Appointment createAppointment(Appointment appointment) {
		Appointment newAppointment = appointmentRepo.save(appointment);	
		return newAppointment;
	}

	@Override
	public Appointment findByAppointment(AppointmentId id) {
		Optional<Appointment> result = appointmentRepo.findById(id);
		
		if(result.isPresent()) {
			return result.get();
		}
		return null;
	}

}
