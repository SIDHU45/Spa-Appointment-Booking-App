package com.sid.project.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.sid.project.dao.UserRepository;
import com.sid.project.dao.UserRoleRepository;
import com.sid.project.entity.User;
import com.sid.project.entity.UserRole;

import jakarta.persistence.EntityExistsException;

@Service
public  class UserServiceImpl implements UserService {
	
	private UserRepository userRepository;
	
	private RoleServiceImpl roleImpl;
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public UserServiceImpl(UserRepository theUserRepository,
								RoleServiceImpl theRoleImpl,
									BCryptPasswordEncoder passwordEncoder) {
		userRepository = theUserRepository;
		roleImpl = theRoleImpl;
		bCryptPasswordEncoder =passwordEncoder;
	}

	@Override
	public List<User> findAll() {		
		return userRepository.findAll();
	}

	@Override
	public User findById(String phn_number) {
		Optional<User> result = userRepository.findById(phn_number);
		if(result.isPresent()) {
			return result.get();
		}
		return null;
	}

	@Override
	public User save(User user) {
		return userRepository.save(user);
	}

	@Override
	public void deleteById(String phn_number) {
		userRepository.deleteById(phn_number);		
	}

	@Override
	public void createAdmin() {
		User admin = new User();
		admin.setName("siddarth");
		admin.setPhn_number("9566927550");
		admin.setEmail("sidhu45@gmail.com");
		admin.setPassword(bCryptPasswordEncoder.encode("Sidhu@2000"));
		admin.setActive(1);
		
		Collection<UserRole> list = new ArrayList<UserRole>();
		list.add(roleImpl.findById(1));
		admin.setRole(list);
		
		userRepository.save(admin);
	}

	@Override
	public User createUser(User theUser) {
		Optional<User> result = userRepository.findById(theUser.getPhn_number());
		
		if(!(result.isPresent())) {
			Collection<UserRole> list = new ArrayList<UserRole>();
			list.add(roleImpl.findById(2));
			theUser.setRole(list);
			theUser.setActive(1);
			theUser.setPassword(bCryptPasswordEncoder.encode(theUser.getPassword()));
			return userRepository.save(theUser);		
		}else {
			throw new EntityExistsException("The Entered Phone Number is already exists");
		}
	}

	

}
