package com.diemme;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.diemme.domain.Admin;
import com.diemme.domain.Client;
import com.diemme.domain.Role;
import com.diemme.repository.AdminRepository;
import com.diemme.repository.ClientRepository;

@Component
public class TestUserCreate implements CommandLineRunner{
	
	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		Admin admin = new Admin();
		Client client = new Client();
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		Set<Role> roleAdminList = new HashSet<Role>();
		Role roleAdmin = new Role();
		/*
		 * roleAdmin.setName("admin"); roleAdmin.setDescription("this is an admin");
		 * roleAdminList.add(roleAdmin);
		 */
		

		admin.setActive(true);
		admin.setCountry("Italy");
		admin.setEmail("alexraimondi91@gmail.com");
		admin.setName("alex");
		admin.setPassword(passwordEncoder.encode("alex"));
		admin.setSurname("raimondi");
		
		client.setActive(true);
		client.setCountry("Italy");
		client.setEmail("ale_1994@gmail.com");
		client.setFiscalCode("RMNLXA91A14E058I");
		client.setName("alessandro");
		client.setPassword(passwordEncoder.encode("alex"));
		client.setSurname("sallese");
		client.setAddressShipment("via ciao ciao");
		
		
		if(!adminRepository.findByEmail("alexraimondi91@gmail.com").isPresent()) {
			adminRepository.save(admin);
		}
		

		if(!adminRepository.findByEmail("ale_1994@gmail.com").isPresent()) {
			clientRepository.save(client);

		}
	
	}


}
