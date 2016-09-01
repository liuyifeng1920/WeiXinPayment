package com.miteno.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.miteno.dao.OrderQueryDao;
import com.miteno.entity.OrderData;
import com.miteno.service.OrderQueryService;
import com.miteno.util.PageView;
@Transactional
@Service("orderqueryservice")
public class OrderQueryServiceImpl implements OrderQueryService{
	@Autowired
	private OrderQueryDao  orderquerydao;

	 /**
	  * 进入订单查询页面
	  */
	public PageView query(PageView pageView, OrderData orderdata) {
		List<OrderData> list = orderquerydao.query(pageView, orderdata);
		pageView.setRecords(list);
		return pageView;
	}
}
