package com.miteno.dao;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.miteno.base.BaseDao;
import com.miteno.entity.Business_pay;


public interface TradeDao extends BaseDao<Business_pay>{

	Business_pay findByOutTradeNo(String outTradeNo);

	List<Business_pay> findMap(Map<String, Object> map);

	int findDaySUM(String businessid);

	int findMouthSUM(String businessid);

}
