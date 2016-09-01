package com.miteno.dao;

import java.util.List;

import com.miteno.base.BaseDao;
import com.miteno.entity.Phoneaccess;
import com.miteno.entity.Product;


public interface MobDao extends BaseDao<Product>  {
	public List<String> queryFace();

//返回所有优惠价
	public List<String> searchYhPrice();
//查询面值
	public String searchPrice(String money);

}
