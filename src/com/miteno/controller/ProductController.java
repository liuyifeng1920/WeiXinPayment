package com.miteno.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.miteno.entity.BillingChannel;
import com.miteno.entity.Product;
import com.miteno.service.ProductService;
import com.miteno.util.Common;
import com.miteno.util.PageView;

//商家
@Controller
@RequestMapping("/background/product/")
public class ProductController {
	@Autowired
	private ProductService productService;

	// 进入查询页面
	@RequestMapping("query")
	public String query(Model model, Product product, String pageNow) {
		PageView pageView = null;
		if (Common.isEmpty(pageNow)) {
			pageView = new PageView(1);
		} else {
			pageView = new PageView(Integer.parseInt(pageNow));
		}
		pageView = productService.query(pageView, product);
		model.addAttribute("pageView", pageView);
		return "/background/product/list";
	}

	// 进入添加页面
	@RequestMapping("addUI")
	public String addUI(HttpServletRequest request) {
		Long businessid = productService.getMaxbusinessid();
		businessid += 1L;
		request.setAttribute("productid", businessid);
		List<BillingChannel> list = productService.findAll();
		request.setAttribute("appname", list);
		System.out.println("++++" + list);
		return "/background/product/add";
	}

	// 保存
	@RequestMapping("add")
	public String add(Model model, Product product) {
		try {
			productService.add(product);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:query.action";
	}

	// 修改页面
	@RequestMapping("getById")
	public String getById(Model model, String productId, int type) {
		Product product = productService.getById(productId);
		model.addAttribute("product", product);
	/*	List<BillingChannel> list = productService.findAll();
		model.addAttribute("appname", list);*/
		return "/background/product/edit";
	}

	// 修改保存
	@RequestMapping("update")
	public String update(Model model, Product product) {
		productService.modify(product);
		return "redirect:query.action";
	}

	// 删除
	@RequestMapping("deleteById")
	public String deleteById(Model model, String productId) {
		productService.delete(productId);
		return "redirect:query.action";
	}
}
