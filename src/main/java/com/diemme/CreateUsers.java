package com.diemme;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.diemme.domain.mongo.Chat;
import com.diemme.domain.mongo.ChatType;
import com.diemme.domain.mongo.Message;
import com.diemme.domain.mysql.Role;
import com.diemme.domain.mysql.RoleType;
import com.diemme.domain.mysql.User;
import com.diemme.repository.mongo.ChatRepository;
import com.diemme.repository.mongo.MessageRepository;
import com.diemme.repository.mysql.RoleRepository;
import com.diemme.repository.mysql.UserRepository;

@Component
public class CreateUsers implements CommandLineRunner {

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ChatRepository chatRepository;


	@Override
	public void run(String... args) throws Exception {

		if (userRepository.findByEmail("alexraimondi91@gmail.com") == null) {

			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

			Role roleAdmin = new Role();
			User userAdmin = new User();

			roleAdmin.setRole("ADMIN");
			Set<Role> roleListUser1Test = new HashSet<Role>();
			roleListUser1Test.add(roleAdmin);

			userAdmin.setActive(true);
			userAdmin.setEmail("alexraimondi91@gmail.com");
			userAdmin.setName("alex");
			userAdmin.setPassword(passwordEncoder.encode("alex"));
			userAdmin.setSurname("raimondi");
			userAdmin.setRoles(roleListUser1Test);
			userAdmin.setUserName("alexraimondi91@gmail.com");

			roleRepository.save(roleAdmin);
			userRepository.save(userAdmin);

		}

		if (userRepository.findByEmail("ale_1994@gmail.com") == null) {

			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

			Role roleClient = new Role();
			User userClient = new User();

			roleClient.setRole("CLIENT");
			Set<Role> roleListUser2Test = new HashSet<Role>();
			roleListUser2Test.add(roleClient);

			userClient.setActive(true);
			userClient.setEmail("ale_1994@gmail.com");
			userClient.setFiscalCode("RMNLXA91A14E058I");
			userClient.setName("alessandro");
			userClient.setPassword(passwordEncoder.encode("alex"));
			userClient.setSurname("sallese");
			userClient.setAddressShipment("via ciao ciao");
			userClient.setRoles(roleListUser2Test);
			userClient.setUserName("ale_1994@gmail.com");

			roleRepository.save(roleClient);
			userRepository.save(userClient);
		}

		if (userRepository.findByEmail("a.raimondi@gunpowder.eu") == null) {

			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

			Role roleDesigner = new Role();
			Role roleAdmin = roleRepository.findByRole("ADMIN");
			User userDesigner = new User();

			roleDesigner.setRole("DESIGNER");
			Set<Role> roleListUser3Test = new HashSet<Role>();
			roleListUser3Test.add(roleDesigner);
			roleListUser3Test.add(roleAdmin);

			userDesigner.setActive(true);
			userDesigner.setEmail("a.raimondi@gunpowder.eu");
			userDesigner.setName("alex");
			userDesigner.setPassword(passwordEncoder.encode("alex"));
			userDesigner.setSurname("raimondi");
			userDesigner.setRoles(roleListUser3Test);
			userDesigner.setUserName("a.raimondi@gunpowder.eu");
			userDesigner.setFiscalCode("RMNLXA91A14E058I");
			userDesigner.setPIva("12345678");
			userDesigner.setCompanyName("Diemme S.R.L.");

			roleRepository.save(roleDesigner);
			userRepository.save(userDesigner);

		}

		if (userRepository.findByEmail("alex@alex.it") == null) {

			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

			Role roleProductor = new Role();
			User userProductor = new User();

			roleProductor.setRole("PRODUCTOR");
			Set<Role> roleListUser4Test = new HashSet<Role>();
			roleListUser4Test.add(roleProductor);

			userProductor.setActive(true);
			userProductor.setEmail("alex@alex.it");
			userProductor.setName("alex");
			userProductor.setPassword(passwordEncoder.encode("alex"));
			userProductor.setSurname("raimondi");
			userProductor.setRoles(roleListUser4Test);
			userProductor.setUserName("alex@alex.it");
			userProductor.setFiscalCode("RMNLXA91A14E058I");
			userProductor.setPIva("12345678");
			userProductor.setCompanyName("Maglietta Factory S.R.C.");

			roleRepository.save(roleProductor);
			userRepository.save(userProductor);

		}
		
		/*
		 * ChatType chatType = new ChatType(); Chat chat = new Chat(); Message message =
		 * new Message(); Set<Message> messages = new HashSet<Message>();
		 * 
		 * chatType.setName("user/client"); chatTypeRepository.save(chatType);
		 * message.setDate(LocalDateTime.now()); message.setMessage("prova");
		 * messages.add(message); chat.setChatType(chatType);
		 * chat.setMessages(messages); chatRepository.save(chat);
		 */
		 

	}

}
