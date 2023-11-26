package com.sid.project.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sid.project.dao.CategoryRepository;
import com.sid.project.dao.DepartmentRepo;
import com.sid.project.dao.StylistRepo;
import com.sid.project.entity.SpaCategory;
import com.sid.project.entity.SpaDepartment;
import com.sid.project.entity.SpaStylists;

@Service
public class DepartmentServiceImpl implements DepartmentService {

	private DepartmentRepo departmentRepo;
	
	private CategoryRepository categoryRepository;
	
	private StylistRepo stylistRepo;
	
	
	public DepartmentServiceImpl(DepartmentRepo theDepartmentRepo , 
												CategoryRepository theCategoryRepository,
												StylistRepo theStylistRepo) {
		departmentRepo = theDepartmentRepo;
		categoryRepository = theCategoryRepository;
		stylistRepo = theStylistRepo;
	}
	@Override
	public void createDepatment() {
		
		SpaCategory category = categoryRepository.findById("Men").get();
		SpaCategory category1 = categoryRepository.findById("Women").get();
		
		System.out.println(category);
		
		SpaDepartment dept1 = new SpaDepartment(0,"Hair Colouring", category);
		SpaDepartment dept2 = new SpaDepartment(0,"Groom Makeup" , category );
		SpaDepartment dept3 = new SpaDepartment(0,"Hair Colouring and Cutting", category1 );
		SpaDepartment dept4 = new SpaDepartment(0,"Laser Hair Removal" , category1);
		
		List<SpaDepartment> men = Arrays.asList(dept1 , dept2);
		List<SpaDepartment> women = Arrays.asList(dept3,dept4);
		
		category.setDepartment(men);
		category1.setDepartment(women);
		
		categoryRepository.save(category);
		categoryRepository.save(category1);
		
	}
	@Override
	public SpaDepartment save(SpaDepartment spaDepartment) {
		return departmentRepo.save(spaDepartment);
	}

	@Override
	public SpaDepartment findById(int id) {
		Optional<SpaDepartment> result = departmentRepo.findById(id);
		if(result.isPresent()) {
			return result.get();
		}
		return null;
	}



}
