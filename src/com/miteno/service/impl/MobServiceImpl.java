package com.miteno.service.impl;

import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.miteno.dao.MobDao;
import com.miteno.dao.OrderDao;
import com.miteno.dao.PhoneaccessbDao;
import com.miteno.entity.OrderData;
import com.miteno.entity.Phoneaccess;
import com.miteno.entity.Product;
import com.miteno.service.MobService;
import com.miteno.util.PageView;



@Transactional
@Service("mobService")
public class MobServiceImpl implements MobService{
	@Autowired
	private MobDao mobDao; //对应实体 Product
	@Autowired
	private OrderDao orderdao; //对应实体 Order
	@Autowired
	private PhoneaccessbDao phoneaccessbdao;//对应实体Phoneaccess
//返回归属地
	public Phoneaccess searchmobileBelong(String mobile) {
		return phoneaccessbdao.searchmobileBelong(mobile);
	}
//查询历史分页
	public PageView searchHistory(PageView pageView, OrderData order) {
		List<OrderData> list =orderdao.query(pageView, order);
		pageView.setRecords(list);
		return pageView;
	}
//查询历史记录
	public List<OrderData> searchOrder(OrderData data){
		return orderdao.searchOrder(data);
	}
	//查询面值
	public List<String> queryFace() {
		return mobDao.queryFace();
	}

	//更新订单号
	public void saveOrder(OrderData od) {
		orderdao.saveOrder(od);
	}
	// 返回付款界面的信息
	public OrderData searchpayDataList(String param) {
		return orderdao.searchpayDataList(param);
	}
//查询所有优惠价
	public List<String> searchYhPrice() {
		return mobDao.searchYhPrice();
	}
	@Override
	public void updateOrder(HashMap<String, String> map) {
		orderdao.updateOrder(map);
		
	}
	@Override
	public void updateOrderBySendOrderNumber(HashMap<String, String> map) {
		orderdao.updateOrderBySendOrderNumber(map);
	}
	//查询面值
	public String searchPrice(String money) {
		return mobDao.searchPrice(money);
	}

	

	
	
	
	
	
	
	
	
	
}
