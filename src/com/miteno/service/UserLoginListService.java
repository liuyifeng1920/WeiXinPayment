package com.miteno.service;

import com.miteno.entity.UserLoginList;
import com.miteno.util.PageView;

public interface UserLoginListService {
	public PageView query(PageView pageView,UserLoginList userLoginList);
	
	public void add(UserLoginList userLoginList);

}
