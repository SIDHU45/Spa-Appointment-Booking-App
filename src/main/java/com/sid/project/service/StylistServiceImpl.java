package com.sid.project.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sid.project.dao.DepartmentRepo;
import com.sid.project.dao.StylistRepo;
import com.sid.project.entity.SpaDepartment;
import com.sid.project.entity.SpaStylists;

@Service
public class StylistServiceImpl implements StylistService {
	
	private StylistRepo stylistRepo;
	
	private DepartmentRepo departmentRepo;
	
	@Autowired
	public StylistServiceImpl(StylistRepo theStylistRepo,
			DepartmentRepo theDepartmentRepo) {
		stylistRepo =theStylistRepo;
		departmentRepo = theDepartmentRepo;
	}

	@Override
	public List<SpaStylists> findAll() {		
		return stylistRepo.findAll();
	}

	@Override
	public SpaStylists findById(int id) {
		Optional<SpaStylists> result = stylistRepo.findById(id);
		if(result.isPresent()) {
			return result.get();
		}
		return null;
	}

	@Override
	public SpaStylists save(SpaStylists spaStylists) {
		return stylistRepo.save(spaStylists);
	}

	@Override
	public void createStylist() {
		
		SpaDepartment department1 = departmentRepo.findById(1).get();
		SpaDepartment department2 = departmentRepo.findById(2).get();
		SpaDepartment department3 = departmentRepo.findById(3).get();
		SpaDepartment department4 = departmentRepo.findById(4).get();
		
		
		SpaStylists stylist1 = new SpaStylists(1,"Mr.John Wilson" , department1);
		SpaStylists stylist2 = new SpaStylists(2,"Mr.Antony Raj" , department1);
		SpaStylists stylist3 = new SpaStylists(3,"Mr.Shrukh Patel" , department2);
		SpaStylists stylist4 = new SpaStylists(4,"Mr.Samuel Raj" , department2);
		SpaStylists stylist5 = new SpaStylists(5,"Ms.Hema" , department3);
		SpaStylists stylist6 = new SpaStylists(6,"Ms.Ann Elizabeth" , department3);
		SpaStylists stylist7 = new SpaStylists(7,"Ms.Shalini" , department4);
		SpaStylists stylist8 = new SpaStylists(8,"Mrs.Josephine Raj" , department4);
		
		
		List<SpaStylists> menHair = Arrays.asList(stylist1,stylist2);
		List<SpaStylists> menMakeUp = Arrays.asList(stylist3,stylist4);
		List<SpaStylists> womenHair = Arrays.asList(stylist5,stylist6);
		List<SpaStylists> womenMakeUp = Arrays.asList(stylist7,stylist8);
		
		department1.setStylist(menHair);
		department2.setStylist(menMakeUp);
		department3.setStylist(womenHair);
		department4.setStylist(womenMakeUp);
		
		departmentRepo.save(department1);
		departmentRepo.save(department2);
		departmentRepo.save(department3);
		departmentRepo.save(department4);
		
	}

}
