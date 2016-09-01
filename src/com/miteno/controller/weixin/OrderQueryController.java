package com.miteno.controller.weixin;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.miteno.entity.OrderData;
import com.miteno.service.OrderQueryService;
import com.miteno.util.Common;
import com.miteno.util.DateUtils;
import com.miteno.util.PageView;

/**
 * 后台管理订单查询
 */
@Controller
@RequestMapping("/background/order/")
public class OrderQueryController {
	@Autowired
	private OrderQueryService orderqueryservice;
	 /**
	  * 进入订单查询页面
	  * 默认不查数据
	  */
	 @RequestMapping("query")
	 public String query(Model model, OrderData orderdata, String pageNow) {
			PageView pageView = null;
			if (Common.isEmpty(pageNow)) {
				pageView = new PageView(1);
			} else {
				pageView = new PageView(Integer.parseInt(pageNow));
			}
			if(StringUtils.isNotEmpty(orderdata.getPhone_number())
					||	StringUtils.isNotEmpty(orderdata.getInner_order_number())
					||	StringUtils.isNotEmpty(orderdata.getSend_order_number())
					||	StringUtils.isNotEmpty(orderdata.getStartDate())
					||	StringUtils.isNotEmpty(orderdata.getEndDate())
					||	StringUtils.isNotEmpty(orderdata.getOrder_status())
			){
				pageView = orderqueryservice.query(pageView, orderdata);
			}else{
				orderdata.setStartDate(DateUtils.getDate());
				pageView = orderqueryservice.query(pageView, orderdata);
			}
				
				model.addAttribute("pageView", pageView);
				model.addAttribute("order_status", orderdata.getOrder_status());
			return "/background/order/list";
		}
	 
}
