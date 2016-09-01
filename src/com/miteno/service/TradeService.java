package com.miteno.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.miteno.entity.Business_pay;
import com.miteno.util.PageView;

public interface TradeService {

	public PageView query(PageView pageView, Business_pay businesspay);

	public Business_pay findByOutTradeNo(String outTradeNo);

	public void add(Business_pay pay);

	public List<Business_pay> findMap(Map<String, Object> map);

	public int findDaySUM(String businessid);

	public int findMouthSUM(String businessid);

}
