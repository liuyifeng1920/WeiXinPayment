package com.miteno.dao;


import com.miteno.base.BaseDao;
import com.miteno.entity.Roles;
import com.miteno.entity.User;


public interface UserDao extends BaseDao<User>{
	public int countUser(String userName,String userPassword);
	
	public User querySingleUser(String userName);
	
	public Roles findbyUserRole(String userId);
}
