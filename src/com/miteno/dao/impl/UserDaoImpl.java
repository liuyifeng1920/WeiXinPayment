package com.miteno.dao.impl;


import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.miteno.base.impl.BaseDaoImpl;
import com.miteno.dao.UserDao;
import com.miteno.entity.Roles;
import com.miteno.entity.User;


@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao
{

	public int countUser(String userName, String userPassword) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("userName", userName);
		map.put("userPassword", userPassword);
		return (Integer)getSqlSession().selectOne("user.countUser",map);
	}

	public User querySingleUser(String userName) {
		return (User)getSqlSession().selectOne("user.queryUserName",userName);
	}

	public Roles findbyUserRole(String userId) {
		return (Roles)getSqlSession().selectOne("roles.findbyUserRole", userId);
	}
	
}
