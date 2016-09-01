package com.miteno.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.miteno.base.impl.BaseDaoImpl;
import com.miteno.dao.ProductDao;
import com.miteno.entity.Agent;
import com.miteno.entity.BillingChannel;
import com.miteno.entity.Product;
import com.miteno.util.PageView;

@Repository("productDao")
public class ProductDaoImpl extends BaseDaoImpl<Product> implements ProductDao {
	@Override
	public List<BillingChannel> findAll() {
		return getSqlSession().selectList("agent.searchSelect");
	}

	@Override
	public Long getMaxbusinessid() {
		return getSqlSession().selectOne("agent.MaxAgent");
	}

	public Product getAgentAndBillingByBusinessid(String businessid) {
		return getSqlSession().selectOne("agent.getAgentAndBillingByBusinessid",businessid);
	}

}
