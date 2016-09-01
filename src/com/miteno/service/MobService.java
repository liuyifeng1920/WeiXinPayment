package com.miteno.service;

import java.util.HashMap;
import java.util.List;

import com.miteno.entity.OrderData;
import com.miteno.entity.Phoneaccess;
import com.miteno.entity.Product;
import com.miteno.util.PageView;



public interface MobService {


	public Phoneaccess searchmobileBelong(String mobile);

	public PageView searchHistory(PageView pageView, OrderData order);

	public List<String> queryFace();

	public List<OrderData> searchOrder(OrderData data);

	public OrderData searchpayDataList(String param);

	public void saveOrder(OrderData od);

	public List<String> searchYhPrice();

	public void updateOrder(HashMap<String, String> map);

	public void updateOrderBySendOrderNumber(HashMap<String, String> map);
//返回面值
	public String searchPrice(String money);


}
