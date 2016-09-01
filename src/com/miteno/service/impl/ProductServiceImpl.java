package com.miteno.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.miteno.dao.ProductDao;
import com.miteno.entity.Agent;
import com.miteno.entity.BillingChannel;
import com.miteno.entity.Product;
import com.miteno.service.ProductService;
import com.miteno.util.PageView;

@Transactional
@Service("productService")
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductDao productDao;

	@Override
	public PageView query(PageView pageView, Product product) {
		List<Product> list = productDao.query(pageView, product);
		pageView.setRecords(list);
		return pageView;
	}

	@Override
	public void add(Product product) {
		try {
			productDao.add(product);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<BillingChannel> findAll() {
		return productDao.findAll();
	}

	@Override
	public Long getMaxbusinessid() {
		return productDao.getMaxbusinessid();
	}

	@Override
	public Product getById(String agentId) {
		return productDao.getById(agentId);
	}

	@Override
	public void modify(Product product) {
		try {
			productDao.modify(product);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void delete(String agentId) {
		productDao.delete(agentId);
	}

	public Product getAgentAndBillingByBusinessid(String businessid) {
		return productDao.getAgentAndBillingByBusinessid(businessid);
	}
}
