package com.miteno.dao;

import java.util.List;

import com.miteno.base.BaseDao;
import com.miteno.entity.Agent;
import com.miteno.entity.BillingChannel;
import com.miteno.entity.Product;

public interface ProductDao extends BaseDao<Product> {
	public List<BillingChannel> findAll();

	public Long getMaxbusinessid();

	public Product getAgentAndBillingByBusinessid(String businessid);

}
