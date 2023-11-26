package com.sid.project.controller;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.sid.project.entity.Appointment;
import com.sid.project.entity.AppointmentId;
import com.sid.project.entity.SpaCategory;
import com.sid.project.entity.SpaDepartment;
import com.sid.project.entity.SpaStylists;
import com.sid.project.entity.User;
import com.sid.project.security.AppConfig;
import com.sid.project.service.AppointmentService;
import com.sid.project.service.CategoryService;
import com.sid.project.service.DepartmentService;
import com.sid.project.service.RoleService;
import com.sid.project.service.StylistService;
import com.sid.project.service.UserService;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpSession;

@Controller
public class SpaController {
	
	private StylistService stylistService;
	private UserService userService;
	private RoleService roleService;
	private CategoryService categoryService;
	private DepartmentService departmentService;
	private AppointmentService appointmentService;
	private AppConfig appconfig;
	
	@Autowired
	public SpaController(UserService theUserService,
			RoleService theRoleService,
								StylistService theStylistService,
								CategoryService theCategoryService,
								DepartmentService theDepartmentService,
								AppointmentService theAppointmentService,
								AppConfig theAppConfig) {
		userService = theUserService;
		roleService =theRoleService;	
		categoryService =theCategoryService;
		departmentService=theDepartmentService;
		stylistService = theStylistService;
		appointmentService =theAppointmentService;	
		appconfig = theAppConfig;
		
	}
	
	@PostConstruct
	public void createAdmin() {
		roleService.createRole();
		userService.createAdmin();
		categoryService.createCategory();
		departmentService.createDepatment();
		stylistService.createStylist();
	
	}
	
	@GetMapping("/")
	public String showHomePage(Model theModel) {
		List<SpaCategory> list = categoryService.findAll();
		theModel.addAttribute("category" , list);
		return "process/home";
	}
	@GetMapping("/category/{category}")
	public String getDepartment(@PathVariable String category, Model model,
			HttpSession session) {
		SpaCategory category2 = categoryService.findById(category);
		
		session.setAttribute("foundcategory", category2);
		
		model.addAttribute("category", category2);
		return "category";
	}
	@GetMapping("/department/{department}")
	public String getDepartment(@PathVariable int department, Model model,
			HttpSession session) {
		SpaDepartment foundDepartment = departmentService.findById(department);
		
		session.setAttribute("department", foundDepartment);
		
		model.addAttribute("department", foundDepartment);
		return "department";
	}
	
	@GetMapping("/stylist/{id}")
	public String getDoctor(@PathVariable int id, HttpSession session, Model model) {
		SpaStylists stylist = stylistService.findById(id);
		session.setAttribute("stylist", stylist);
		model.addAttribute("stylist" , stylist);
	LocalDate date = LocalDate.now();
		
		List<Appointment> list =  new ArrayList<>();
		for(int i = 0; i < 6; i++) {
			LocalDate date2 = LocalDate.parse(date.toString()).plusDays(1);
			AppointmentId appointmentId = new AppointmentId();
			appointmentId.setDate(date2.toString());
			appointmentId.setStylist(stylist);
			Appointment appointment = appointmentService.findByAppointment(appointmentId);
			if (appointment == null) {
				Appointment appointmentNew = new Appointment();
				appointmentNew.setId(appointmentId);
				Appointment dbAppointment = appointmentService.createAppointment(appointmentNew);
				list.add(dbAppointment);
			}
			else {
				list.add(appointment);}
			date = date2;
		}
		session.setAttribute("selectedList", list);		
		return "stylist";
	}
	
	@GetMapping("/appointments/{id}/{date}")
	public String selectDate(@PathVariable("date") String date, @PathVariable("id") int id,
					HttpSession session) {
		AppointmentId appointmentId = new AppointmentId();
		appointmentId.setStylist(stylistService.findById(id));
		appointmentId.setDate(date);
		Appointment appointment = appointmentService.findByAppointment(appointmentId);
		session.setAttribute("appointment", appointment);
		session.setAttribute("appointmentId", appointmentId);
		return "book";
	}
	
	@GetMapping("/book")
	public String selectTime(@RequestParam int time, HttpSession session,
			 Authentication authentication,Model model) {
		SpaStylists stylist = (SpaStylists) session.getAttribute("stylist");
		SpaDepartment department = (SpaDepartment) session.getAttribute("department");
		AppointmentId appointmentId = (AppointmentId) session.getAttribute("appointmentId");


		appointmentId.setStylist(stylist);
		
		Appointment appointment = appointmentService.findByAppointment(appointmentId);
		String phNo = authentication.getName();
		User user = userService.findById(phNo);
		String deptName = department.getDeptName();
		Appointment newAppointment = appconfig.setHour(time, appointment, phNo);
		List<Appointment> appointments = user.getAppointments();
		appointments.add(newAppointment);
		user.setAppointments(appointments);
		User dbUser = userService.save(user);
		model.addAttribute("user", dbUser);
		model.addAttribute("department", deptName);
		model.addAttribute("appointments", appointments);
		model.addAttribute("stylist", appointmentId.getStylist().getName());
		return "redirect:/appointments";
	}
	
	@GetMapping("/appointments")
	public String myAppointments(Authentication authentication, Model model) {
		String name = authentication.getName();
		User user = userService.findById(name);
		
		List<Appointment> appointments = user.getAppointments();
		
		model.addAttribute("user", user);
		model.addAttribute("appointments", appointments);
		return "appointment-page";
	}
}
