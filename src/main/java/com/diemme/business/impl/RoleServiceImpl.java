package com.diemme.business.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.diemme.business.RoleService;
import com.diemme.domain.mysql.Role;
import com.diemme.repository.mysql.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	RoleRepository roleRepository;

	@Override
	@Transactional
	public Role findByRole(String name) {

		return roleRepository.findByRole(name);
	}

}
