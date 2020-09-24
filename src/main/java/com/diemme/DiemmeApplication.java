package com.diemme;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.diemme.domain.mysql.Role;



@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan("com.diemme")
public class DiemmeApplication {

	
	
	public static void main(String[] args) {
		SpringApplication.run(DiemmeApplication.class, args);
		
		
		
		
	}
	
	

}
