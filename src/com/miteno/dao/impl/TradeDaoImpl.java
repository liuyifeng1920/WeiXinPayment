package com.miteno.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.miteno.base.impl.BaseDaoImpl;
import com.miteno.dao.TradeDao;
import com.miteno.entity.Business_pay;
@Repository("tradeDao")
public class TradeDaoImpl extends  BaseDaoImpl<Business_pay> implements TradeDao{

	

	@Override
	public Business_pay findByOutTradeNo(String outTradeNo) {
		// TODO Auto-generated method stub
		return this.getSqlSession().selectOne("business_pay.findByOutTradeNo", outTradeNo);
	}

	@Override
	public List<Business_pay> findMap(Map<String, Object> map) {
		 List<Business_pay> selectList=null;
		try {
			 selectList = getSqlSession().selectList("business_pay.findMap",map);
			 
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return	selectList;
	}

	@Override
	public int findDaySUM(String businessid) {
		
		
		return 	this.getSqlSession().selectOne("business_pay.findDaySUM", businessid);
		
	}

	@Override
	public int findMouthSUM(String businessid) {
		return 	this.getSqlSession().selectOne("business_pay.findMouthSUM", businessid);
	}

}
