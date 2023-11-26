package com.sid.project.security;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {
		
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public UserDetailsManager userDetails(DataSource dataSource) {
		JdbcUserDetailsManager detailsManager = new JdbcUserDetailsManager(dataSource);
		
		detailsManager.setUsersByUsernameQuery("select number, password, active from user where number=?");
		detailsManager.setAuthoritiesByUsernameQuery("select number, role from userrole where number=?");
		
		
		return detailsManager;
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		http.authorizeHttpRequests(configurer ->
		              configurer
		              .requestMatchers("/signup_page").permitAll()
		              .requestMatchers("/register").permitAll()
		              .anyRequest().authenticated()
		                               )
					.formLogin(form ->
		                form
		                .loginPage("/login")
		                .loginProcessingUrl("/loginProcess")
		                .defaultSuccessUrl("/")
		                .permitAll())
					.logout(logout -> logout.permitAll())
					.exceptionHandling(configurer ->
								configurer
								.accessDeniedPage("/access-denied-page")
								);
		return http.build();
	}
}
