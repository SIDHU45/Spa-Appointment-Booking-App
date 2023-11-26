package com.sid.project.entity;

import java.util.Collection;
import java.util.List;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name="spa_Category")
public class SpaCategory {
	
	@Id
	private String category;
	
	@OneToMany(fetch = FetchType.EAGER)
	@Cascade(CascadeType.ALL)
	private List<SpaDepartment> department;
	
	
	
}
