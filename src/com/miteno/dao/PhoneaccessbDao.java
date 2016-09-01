package com.miteno.dao;


import com.miteno.base.BaseDao;
import com.miteno.entity.Phoneaccess;


public interface PhoneaccessbDao extends BaseDao<Phoneaccess>  {
	public Phoneaccess searchmobileBelong(String mobile);
}
