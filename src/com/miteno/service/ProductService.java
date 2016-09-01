package com.miteno.service;

import java.util.List;

import com.miteno.entity.Agent;
import com.miteno.entity.BillingChannel;
import com.miteno.entity.Product;
import com.miteno.util.PageView;

public interface ProductService {

	public PageView query(PageView pageView, Product product);

	public void add(Product product);
	public List<BillingChannel> findAll();

	public Long getMaxbusinessid();

	public Product getById(String agentId);

	public void modify(Product product);

	public void delete(String agentId);

	public Product getAgentAndBillingByBusinessid(String businessid);
}
