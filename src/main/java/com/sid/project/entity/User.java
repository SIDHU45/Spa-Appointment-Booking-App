package com.sid.project.entity;

import java.util.Collection;
import java.util.List;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class User {
	
	@NotBlank(message = "*")
	@Column(name="name")
	private String name;
	@NotBlank(message = "*")
	@Column(name="email")
	private String email;
	@Id
	@NotBlank(message = "*")
	@Column(name="number")
	private String phn_number;
	
	
	@Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^*-]).{8,}$",
		message = "*")
	private String password;
	
	private int active;
	@ManyToMany(fetch = FetchType.EAGER)
	@Cascade(CascadeType.ALL)
	@JoinTable(name = "userrole",
			joinColumns = @JoinColumn(name = "number"),
			inverseJoinColumns = @JoinColumn(name = "role"))
	private Collection<UserRole> role;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@Cascade(CascadeType.MERGE)
	private List<Appointment> appointments;
}
