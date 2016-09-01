package com.miteno.service;


import com.miteno.entity.Roles;
import com.miteno.entity.User;
import com.miteno.util.PageView;

public interface UserService{
	public PageView query(PageView pageView,User user);
	
	public void add(User user);
	
	public void delete(String id);
	
	public void modify(User user);
	
	public User getById(String id);
	
	public int countUser(String userName,String userPassword);
	
	public User querySingleUser(String userName);
	
	public Roles findbyUserRole(String userId);
}
