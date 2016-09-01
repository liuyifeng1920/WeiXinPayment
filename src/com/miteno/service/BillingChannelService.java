package com.miteno.service;

import com.miteno.entity.BillingChannel;
import com.miteno.util.PageView;

public interface BillingChannelService {

	public  PageView query(PageView pageView, BillingChannel billingchannel);

	public Long getMaxAppid();

	public void add(BillingChannel billingchannel);

	public BillingChannel getById(String appid);

	public void modify(BillingChannel billingchannel);

	public void delete(String appid);

}
