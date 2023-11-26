package com.sid.project.entity;

import java.util.List;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Appointment {
	
	@EmbeddedId
	private AppointmentId id;
	
	private String hour9;
	private String hour10;
	private String hour11;
	private String hour2;
	private String hour4;
	
	@ManyToMany(mappedBy = "appointments",fetch = FetchType.EAGER)
	private List<User> users;
	

	
	
}
