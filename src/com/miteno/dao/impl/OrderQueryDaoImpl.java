package com.miteno.dao.impl;

import org.springframework.stereotype.Repository;

import com.miteno.base.impl.BaseDaoImpl;
import com.miteno.dao.OrderQueryDao;
import com.miteno.entity.OrderData;

@Repository("orderquerydao")
public class OrderQueryDaoImpl extends BaseDaoImpl<OrderData> implements OrderQueryDao{
	
}
