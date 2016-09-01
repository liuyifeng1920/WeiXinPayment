package com.miteno.dao;


import java.util.List;

import com.miteno.base.BaseDao;
import com.miteno.entity.Roles;
import com.miteno.entity.UserRoles;


public interface RolesDao extends BaseDao<Roles>{
	public List<Roles> findAll();
	public void deleteUserRole(String userId);
	public void saveUserRole(UserRoles userRoles);
}
