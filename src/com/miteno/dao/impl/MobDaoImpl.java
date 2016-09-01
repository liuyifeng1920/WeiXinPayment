package com.miteno.dao.impl;

import java.util.List;

import net.sf.json.JSONObject;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.miteno.base.impl.BaseDaoImpl;
import com.miteno.dao.MobDao;
import com.miteno.entity.Phoneaccess;
import com.miteno.entity.Product;
import com.miteno.util.PageView;


@Repository("mobDao")
public class MobDaoImpl   extends BaseDaoImpl<Product>  implements MobDao {
	//查询面值
	public List<String> queryFace() {
		return getSqlSession().selectList("product.queryFace");
	}

	//返回所有优惠价
	public List<String> searchYhPrice() {
		return getSqlSession().selectList("product.searchYhPrice");
	}

	//返回面值
	public String searchPrice(String money) {
		return getSqlSession().selectOne("product.searchPrice", money);
	}

}
