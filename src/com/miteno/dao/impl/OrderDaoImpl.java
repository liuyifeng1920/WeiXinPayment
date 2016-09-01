package com.miteno.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.miteno.base.impl.BaseDaoImpl;
import com.miteno.dao.OrderDao;
import com.miteno.entity.OrderData;



@Repository("orderdao")
public class OrderDaoImpl   extends BaseDaoImpl<OrderData>  implements OrderDao {

	public void saveOrder(OrderData od) {
		getSqlSession().insert("orderdata.saveOrder",od);
	}
//查询历史记录
	public List<OrderData> searchOrder(OrderData data){
//		Map<Object, Object> map = new HashMap<Object, Object>();
//		map.put("paging", pageView);
//		map.put("t", t);
		Map<Object,Object> map = new HashMap<Object,Object>();
		map.put("t", data);
		return getSqlSession().selectList("orderdata.searchData",map	);
	}
	// 返回付款界面的信息
	public OrderData searchpayDataList(String param) {
		return getSqlSession().selectOne("orderdata.searchpayDataList",param);
	}
	//根据内部单号更改状态
	@Override
	public void updateOrder(HashMap<String, String> map) {
		
		try {
			getSqlSession().update("orderdata.updateOrder", map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Override
	public void updateOrderBySendOrderNumber(HashMap<String, String> map) {
		try {
			getSqlSession().update("orderdata.updateOrderBySendOrderNumber", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	
}
