package com.miteno.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.miteno.dao.TradeDao;
import com.miteno.entity.Agent;
import com.miteno.entity.Business_pay;
import com.miteno.service.TradeService;
import com.miteno.util.PageView;
@Transactional
@Service("tradeService")
public class TradeServiceImpl implements  TradeService{
 @Autowired
 private TradeDao tradeDao;

@Override
public PageView query(PageView pageView, Business_pay businesspay) {
	List<Business_pay> list = tradeDao.query(pageView, businesspay);
	pageView.setRecords(list);
	return pageView;
}

@Override
public Business_pay findByOutTradeNo(String outTradeNo) {
	return tradeDao.findByOutTradeNo(outTradeNo);
	 
}

@Override
public void add(Business_pay pay) {
	try {
		tradeDao.add(pay);
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	
}

@Override
public List<Business_pay> findMap(Map<String, Object> map) {
	return tradeDao.findMap(map);
}

@Override
public int findDaySUM(String businessid) {
	// TODO Auto-generated method stub
	return tradeDao.findDaySUM(businessid);
}

@Override
public int findMouthSUM(String businessid) {
	// TODO Auto-generated method stub
	return tradeDao.findMouthSUM(businessid);
}


}
