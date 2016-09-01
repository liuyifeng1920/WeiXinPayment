package com.miteno.controller.weixin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.miteno.entity.Agent;
import com.miteno.entity.OrderData;
import com.miteno.entity.weixin.WxOrderQueryPoJo;
import com.miteno.entity.weixin.WxRefundQueryPoJo;
import com.miteno.util.Common;
import com.miteno.util.PageView;

@Controller
@RequestMapping("/background/wxpaypubap")
public class WxPayPubApController {
	
	 //进入查询页面 微信订单查询
	 @RequestMapping("query")
	 public String query(Model model, String transaction_id, String pageNow) {
		 if(transaction_id !=null &&  !"".equals(transaction_id)){
		 OrderData orderdata = new OrderData();
		 orderdata.setInner_order_number(transaction_id);
		 WxOrderQueryPoJo pojo = WxOrderQuery.orderquery(orderdata);
			model.addAttribute("pojo", pojo);
		 }
			return "/background/WxPayPubAp/WxOrderQueryList";
		}
	 //进入查询页面 微信退款订单查询
	 @RequestMapping("wxrefundquery")
	 public String WxRefundQuery(Model model, String transaction_id, String pageNow) {
		 if(transaction_id !=null &&  !"".equals(transaction_id)){
		 OrderData orderdata = new OrderData();
		 orderdata.setInner_order_number(transaction_id);
		 WxRefundQueryPoJo pojo = WxSearRef.WxSearRefund(orderdata);
			model.addAttribute("pojo", pojo);
		 }
			return "/background/WxPayPubAp/WxRefundQueryList";
		}
}
