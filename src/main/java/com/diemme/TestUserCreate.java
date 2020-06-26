package com.diemme;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.diemme.domain.Role;
import com.diemme.domain.RoleType;
import com.diemme.domain.User;
import com.diemme.repository.RoleRepository;
import com.diemme.repository.UserRepository;

@Component
public class TestUserCreate implements CommandLineRunner {

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public void run(String... args) throws Exception {

		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

		Role roleAdmin = new Role();
		Role roleClient = new Role();
		User userAdmin = new User();
		User userClient = new User();


		if ((userRepository.getUserByEmail("alexraimondi91@gmail.com")) == null
				&& (userRepository.getUserByEmail("ale_1994@gmail.com")) == null) {

			roleAdmin.setName(RoleType.ROLE_ADMIN);
			roleAdmin.setDescription("ROLE_ADMIN");
			roleClient.setName(RoleType.ROLE_CLIENT);
			roleClient.setDescription("ROLE_CLIENT");

			Set<Role> roleListUser1Test = new HashSet<Role>();
			roleListUser1Test.add(roleAdmin);

			Set<Role> roleListUser2Test = new HashSet<Role>();
			roleListUser2Test.add(roleClient);
			userAdmin.setActive(true);
			userAdmin.setCountry("Italy");
			userAdmin.setEmail("alexraimondi91@gmail.com");
			userAdmin.setName("alex");
			userAdmin.setPassword(passwordEncoder.encode("alex"));
			userAdmin.setSurname("raimondi");
			userAdmin.setRoles(roleListUser1Test);

			userClient.setActive(true);
			userClient.setCountry("Italy");
			userClient.setEmail("ale_1994@gmail.com");
			userClient.setFiscalCode("RMNLXA91A14E058I");
			userClient.setName("alessandro");
			userClient.setPassword(passwordEncoder.encode("alex"));
			userClient.setSurname("sallese");
			userClient.setAddressShipment("via ciao ciao");
			userClient.setRoles(roleListUser2Test);

			userRepository.save(userClient);

			userRepository.save(userAdmin);
			
			roleRepository.save(roleAdmin);
			roleRepository.save(roleClient);
		}

	}

}
