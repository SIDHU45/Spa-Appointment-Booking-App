package com.sid.project.security;

import org.springframework.context.annotation.Configuration;

import com.sid.project.entity.Appointment;


@Configuration
public class AppConfig {

	public Appointment setHour(int time, Appointment appointment, String phNo) {
		switch (time) {
		case 9:
			appointment.setHour9(phNo);
			break;
		case 10:
			appointment.setHour10(phNo);
			break;
		case 11:
			appointment.setHour11(phNo);
			break;
		case 2:
			appointment.setHour2(phNo);
			break;
		case 4:
			appointment.setHour4(phNo);
			break;
			

		default:
			break;
		}
		return appointment;
	}
}
