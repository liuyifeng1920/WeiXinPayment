package com.miteno.dao.impl;



import java.util.List;

import org.springframework.stereotype.Repository;

import com.miteno.base.impl.BaseDaoImpl;
import com.miteno.dao.RolesDao;
import com.miteno.entity.Roles;
import com.miteno.entity.UserRoles;


@Repository("rolesDao")
public class RolesDaoImpl extends BaseDaoImpl<Roles> implements RolesDao
{

	public List<Roles> findAll() {
		return getSqlSession().selectList("roles.findAll");
	}

	public void saveUserRole(UserRoles userRoles ) {
		getSqlSession().insert("roles.saveUserRole", userRoles);
	}

	public void deleteUserRole(String userId) {
		getSqlSession().delete("roles.deleteUserRole", userId);
	}
}
