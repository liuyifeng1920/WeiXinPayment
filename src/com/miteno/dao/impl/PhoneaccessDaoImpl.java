package com.miteno.dao.impl;


import org.springframework.stereotype.Repository;
import com.miteno.base.impl.BaseDaoImpl;
import com.miteno.dao.PhoneaccessbDao;
import com.miteno.entity.Phoneaccess;


@Repository("phoneaccessbdao")
public class PhoneaccessDaoImpl   extends BaseDaoImpl<Phoneaccess>  implements PhoneaccessbDao {
	//归属地
		public Phoneaccess searchmobileBelong(String mobile) {
			return getSqlSession().selectOne("phoneaccess.searchmobileBelong",mobile);
		}
}
