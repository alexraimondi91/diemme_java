package com.diemme.business;

import java.util.Optional;

import com.diemme.domain.mysql.Role;

public interface RoleService {
	
	public Role  findByRole (String name);

}
