package com.miteno.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.miteno.dao.ResourcesDao;
import com.miteno.entity.ResourceRoles;
import com.miteno.entity.Resources;
import com.miteno.service.ResourcesService;
import com.miteno.util.Common;
import com.miteno.util.PageView;

@Transactional
@Service("resourcesService")
public class ResourcesServiceImpl implements ResourcesService {
	@Autowired
	private ResourcesDao resourcesDao;

	public PageView query(PageView pageView, Resources resources) {
		List<Resources> list = resourcesDao.query(pageView, resources);
		pageView.setRecords(list);
		return pageView;
	}

	public void add(Resources resources) {
		resourcesDao.add(resources);
		
	}

	public void delete(String id) {
		resourcesDao.delete(id);
		
	}

	public Resources getById(String id) {
		return resourcesDao.getById(id);
	}

	public void modify(Resources resources) {
		resourcesDao.modify(resources);
	}

	public List<Resources> findAll() {
		return resourcesDao.findAll();
	}

	public List<Resources> getUserResources(String userId) {
		
		return resourcesDao.getUserResources(userId);
	}
	//<!-- 根据用户Id获取该用户的权限-->
	public List<Resources> getRoleResources(String roleId){
		return resourcesDao.getRoleResources(roleId);
	}
	public void saveRoleRescours(String roleId,List<String> list) {
			resourcesDao.deleteRoleRescours(roleId);
			for (String rId : list) {
				if(!Common.isEmpty(rId)){
					ResourceRoles resourceRoles = new ResourceRoles(); 
					resourceRoles.setRescId(Integer.parseInt(rId));
					resourceRoles.setRoleId(Integer.parseInt(roleId));
					resourcesDao.saveRoleRescours(resourceRoles);
				}
			}
	}

	public List<Resources> getResourcesByUserName(String username) {
		return resourcesDao.getResourcesByUserName(username);
	}

}
