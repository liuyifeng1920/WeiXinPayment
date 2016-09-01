package com.miteno.dao.impl;

import org.springframework.stereotype.Repository;

import com.miteno.base.impl.BaseDaoImpl;
import com.miteno.dao.UserLoginListDao;
import com.miteno.entity.UserLoginList;

@Repository("userLoginListDao")
public class UserLoginListDaoImpl extends BaseDaoImpl<UserLoginList> implements UserLoginListDao{

}
