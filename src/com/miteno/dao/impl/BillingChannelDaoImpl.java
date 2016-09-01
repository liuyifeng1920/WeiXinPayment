package com.miteno.dao.impl;

import org.springframework.stereotype.Repository;

import com.miteno.base.impl.BaseDaoImpl;
import com.miteno.dao.BillingChannelDao;
import com.miteno.entity.BillingChannel;
@Repository("billingChannelDao")
public class BillingChannelDaoImpl extends  BaseDaoImpl<BillingChannel> implements BillingChannelDao{

	@Override
	public Long getMaxAppid() {
		return getSqlSession().selectOne("billingchannel.getMaxId");
	}


}
