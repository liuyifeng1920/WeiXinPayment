package com.miteno.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.miteno.entity.Business_pay;
import com.miteno.service.TradeService;
import com.miteno.util.Common;
import com.miteno.util.PageView;
//交易历史
@Controller
@RequestMapping("/background/trade/")
public class TradeController {
 @Autowired
 private TradeService tradeService;
 
 //查询页面
 @RequestMapping("query")
 public String query(Model model, Business_pay businesspay, String pageNow) {
		PageView pageView = null;
		if (Common.isEmpty(pageNow)) {
			pageView = new PageView(1);
		} else {
			pageView = new PageView(Integer.parseInt(pageNow));
		}
		pageView = tradeService.query(pageView, businesspay);
		model.addAttribute("pageView", pageView);
		return "/background/trade/list";
	}
 
 @InitBinder
 protected void initBinder(WebDataBinder binder) {
     SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
     binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
 }
}
