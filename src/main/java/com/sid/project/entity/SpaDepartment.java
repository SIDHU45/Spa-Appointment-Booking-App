package com.sid.project.entity;



import java.io.Serializable;
import java.util.List;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import jakarta.persistence.Column;
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
public class SpaDepartment  implements Serializable  {
	
	public SpaDepartment(int id,String deptName,SpaCategory category) {
		this.id = id;
		this.deptName = deptName;
		this.category = category;
	}
		
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name="deptName")
	private String deptName;

	@ManyToOne(fetch = FetchType.EAGER)
	private SpaCategory category;
	
	@OneToMany(fetch = FetchType.EAGER)
	@Cascade(CascadeType.ALL)
	private List<SpaStylists> stylist;
}
