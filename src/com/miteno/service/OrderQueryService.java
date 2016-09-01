package com.miteno.service;

import com.miteno.entity.OrderData;
import com.miteno.util.PageView;

public interface OrderQueryService {
	 /**
	  * 进入订单查询页面
	  */
	public PageView query(PageView pageView, OrderData orderdata);

}
