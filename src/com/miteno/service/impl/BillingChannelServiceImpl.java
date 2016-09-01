package com.miteno.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.miteno.dao.BillingChannelDao;
import com.miteno.dao.UserDao;
import com.miteno.entity.BillingChannel;
import com.miteno.entity.Roles;
import com.miteno.entity.User;
import com.miteno.service.BillingChannelService;
import com.miteno.service.UserService;
import com.miteno.util.PageView;

@Transactional
@Service("billingChannelService")
public class BillingChannelServiceImpl implements BillingChannelService {
	@Autowired
	private BillingChannelDao billingChannelDao;

	public PageView query(PageView pageView, BillingChannel billingchannel) {
		List<BillingChannel> list = billingChannelDao.query(pageView, billingchannel);
		pageView.setRecords(list);
		return pageView;
	}
	@Override
	public Long getMaxAppid() {
		return billingChannelDao.getMaxAppid();
	}
		public void add(BillingChannel billingchannel) {
			billingChannelDao.add(billingchannel);
	}
		@Override
	public BillingChannel getById(String appid) {
			return billingChannelDao.getById(appid);
		}
		@Override
		public void modify(BillingChannel billingchannel) {
			billingChannelDao.modify(billingchannel);
			
		}
		@Override
		public void delete(String appid) {
			billingChannelDao.delete(appid);
		}
	
}
