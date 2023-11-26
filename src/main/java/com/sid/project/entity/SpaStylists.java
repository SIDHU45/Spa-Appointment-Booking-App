package com.sid.project.entity;



import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@NoArgsConstructor
public class SpaStylists {
	
	public SpaStylists(int stylistId, String name, SpaDepartment department) {
		this.stylistId = stylistId;
		this.name = name;
		this.department = department;
	}
	
	@Id
	private int stylistId;
	
	private String name;
	
	@OneToMany(fetch = FetchType.EAGER)
	private List<Appointment> appointments;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private SpaDepartment department;
}
