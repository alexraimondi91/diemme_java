package com.diemme.business.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diemme.business.UserService;
import com.diemme.domain.Admin;
import com.diemme.domain.Client;
import com.diemme.domain.Designer;
import com.diemme.domain.Productor;
import com.diemme.domain.User;
import com.diemme.repository.AdminRepository;
import com.diemme.repository.ClientRepository;
import com.diemme.repository.DesignerRepository;
import com.diemme.repository.ProductorRepository;


@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private ProductorRepository productorRepository;
	
	@Autowired
	private DesignerRepository designerRepository;
	
	@Override
	public Optional<User> findByEmail (String email){
		
		Optional<User> user = null;
		Optional<Admin> admin = null;
		Optional<Client> client = null;
		Optional<Productor> productor = null;
		Optional<Designer> designer = null;
		
		if (adminRepository.findByEmail(email).isPresent()) {
			admin = adminRepository.findByEmail(email);			
			user.get().setId(admin.get().getId());
			user.get().setActive((admin.get().getActive()));
			user.get().setCountry(admin.get().getCountry());
			user.get().setEmail(admin.get().getEmail());
			user.get().setMessages(admin.get().getMessages());
			user.get().setName(admin.get().getName());
			user.get().setPassword(admin.get().getPassword());
			user.get().setRoles(admin.get().getRoles());
			user.get().setSurname(admin.get().getSurname());
			
					
		}
		
		else if (clientRepository.findByEmail(email).isPresent()) {
			client = clientRepository.findByEmail(email);		
			user.get().setId(client.get().getId());
			user.get().setActive((client.get().getActive()));
			user.get().setCountry(client.get().getCountry());
			user.get().setEmail(client.get().getEmail());
			user.get().setMessages(client.get().getMessages());
			user.get().setName(client.get().getName());
			user.get().setPassword(client.get().getPassword());
			user.get().setRoles(client.get().getRoles());
			user.get().setSurname(client.get().getSurname());
			
					
		}
		
		else if (productorRepository.findByEmail(email).isPresent()) {
			productor = productorRepository.findByEmail(email);		
			user.get().setId(productor.get().getId());
			user.get().setActive((productor.get().getActive()));
			user.get().setCountry(productor.get().getCountry());
			user.get().setEmail(productor.get().getEmail());
			user.get().setMessages(productor.get().getMessages());
			user.get().setName(productor.get().getName());
			user.get().setPassword(productor.get().getPassword());
			user.get().setRoles(productor.get().getRoles());
			user.get().setSurname(productor.get().getSurname());
			
					
		}
		
		else if (designerRepository.findByEmail(email).isPresent()) {
			designer = designerRepository.findByEmail(email);
			user.get().setId(designer.get().getId());
			user.get().setActive((designer.get().getActive()));
			user.get().setCountry(designer.get().getCountry());
			user.get().setEmail(designer.get().getEmail());
			user.get().setMessages(designer.get().getMessages());
			user.get().setName(designer.get().getName());
			user.get().setPassword(designer.get().getPassword());
			user.get().setRoles(designer.get().getRoles());
			user.get().setSurname(designer.get().getSurname());
			
					
		}
		
		return user;
		
		
	}

}
