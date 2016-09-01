package com.miteno.dao;

import com.miteno.base.BaseDao;
import com.miteno.entity.BillingChannel;

public interface BillingChannelDao extends BaseDao<BillingChannel> {

	public Long getMaxAppid();

}
