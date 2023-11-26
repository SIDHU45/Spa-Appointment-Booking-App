package com.sid.project.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sid.project.dao.UserRoleRepository;
import com.sid.project.entity.UserRole;

@Service
public class RoleServiceImpl implements RoleService {
	
	private UserRoleRepository userRoleRepository;
	
	public RoleServiceImpl(UserRoleRepository theUserRoleRepository) {
		userRoleRepository = theUserRoleRepository;
	}

	@Override
	public UserRole findById(int id) {
		Optional<UserRole> result = userRoleRepository.findById(id);
		if(result.isPresent()) {
			return result.get();
		}		
		return null;
	}

	@Override
	public void deleteById(int id) {
		userRoleRepository.deleteById(id);
		
	}

	@Override
	public UserRole saveRole(UserRole userRole) {		
		return userRoleRepository.save(userRole);
	}

	@Override
	public void createRole() {
		UserRole admin = new UserRole();
		admin.setRole("ROLE_ADMIN");
		
		UserRole user = new UserRole();
		user.setRole("ROLE_USER");
		
		userRoleRepository.save(admin);
		userRoleRepository.save(user);
		
	}
	
	
}
