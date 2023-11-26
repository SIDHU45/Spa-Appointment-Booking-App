package com.sid.project.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sid.project.dao.CategoryRepository;
import com.sid.project.entity.SpaCategory;
import com.sid.project.entity.SpaDepartment;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	private CategoryRepository categoryRepository;
	
	private DepartmentServiceImpl departmentServiceImpl;
	
	public CategoryServiceImpl(CategoryRepository theCategoryRepository,
									DepartmentServiceImpl theDepartmentServiceImpl) {
		departmentServiceImpl = theDepartmentServiceImpl;
		categoryRepository =theCategoryRepository;
	}

	@Override
	public void createCategory() {
		SpaCategory men = new SpaCategory();
		men.setCategory("Men");
		
		SpaCategory women = new SpaCategory();
		women.setCategory("Women");
		
		categoryRepository.saveAll(Arrays.asList(men,women));
		
//		SpaCategory children = new SpaCategory();
//		children.setCategory("Children");
//		categoryRepository.save(children);
//		
		
	}

	@Override
	public SpaCategory findById(String category) {
		Optional<SpaCategory> result = categoryRepository.findById(category);
		if(result.isPresent()) {
			return result.get();
		}
		return null;
	}

	@Override
	public void deleteById(String category) {
		categoryRepository.deleteById(category);
		
	}

	@Override
	public List<SpaCategory> findAll() {
		return categoryRepository.findAll();
	}

	@Override
	public SpaCategory save(SpaCategory category) {
		return categoryRepository.save(category);
	}

}
