package com.miteno.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.miteno.entity.Agent;
import com.miteno.entity.BillingChannel;
import com.miteno.service.BillingChannelService;
import com.miteno.util.Common;
import com.miteno.util.PageView;
//计费渠道
@Controller
@RequestMapping("/background/billing/")
public class BillingChannelController {
 @Autowired
  private BillingChannelService billingChannelService;
 
//进入查询页面
@RequestMapping("query")
public String query(Model model, BillingChannel billingchannel, String pageNow) {
		PageView pageView = null;
		if (Common.isEmpty(pageNow)) {
			pageView = new PageView(1);
		} else {
			pageView = new PageView(Integer.parseInt(pageNow));
		}
		pageView = billingChannelService.query(pageView, billingchannel);
		model.addAttribute("pageView", pageView);
		return "/background/billing/list";
	}
//进入添加页面
	@RequestMapping("addUI")
	public String addUI(HttpServletRequest request) {
		//生成appid
		Long appid = billingChannelService.getMaxAppid();
		appid += 1L;
		request.setAttribute("appid", appid);
		return "/background/billing/add";
	}
	//保存
	@RequestMapping("add")
	public String add(Model model, BillingChannel billingchannel) {
		billingChannelService.add(billingchannel);
		return "redirect:query.action";
	}
	//修改页面
	@RequestMapping("getById")
	public String getById(Model model, String appid, int type) {
		BillingChannel billingchannel = billingChannelService.getById(appid);
		model.addAttribute("bilChan", billingchannel);
		//List<BillingChannel>  list= billingChannelService.findAll();
		//model.addAttribute("appname", list);
		return "/background/billing/edit";
	}
	//修改保存
	@RequestMapping("update")
	public String update(Model model, BillingChannel billingchannel) {
		billingChannelService.modify(billingchannel);
		return "redirect:query.action";
	}
	//删除
	@RequestMapping("deleteById")
	public String deleteById(Model model, String appid) {
		billingChannelService.delete(appid);
		return "redirect:query.action";
	}
}
