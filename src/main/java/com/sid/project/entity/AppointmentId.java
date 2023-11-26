package com.sid.project.entity;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import jakarta.persistence.Embeddable;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
public class AppointmentId {
	
	private String date;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@Cascade(CascadeType.ALL)
	private SpaStylists stylist;
}
