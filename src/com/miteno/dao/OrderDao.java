package com.miteno.dao;

import java.util.HashMap;
import java.util.List;

import com.miteno.base.BaseDao;
import com.miteno.entity.OrderData;

public interface OrderDao extends BaseDao<OrderData>  {

	public void saveOrder(OrderData od);

	public List<OrderData> searchOrder(OrderData data);

	public OrderData searchpayDataList(String param);

	public void updateOrder(HashMap<String, String> map);

	public void updateOrderBySendOrderNumber(HashMap<String, String> map);

	
}
