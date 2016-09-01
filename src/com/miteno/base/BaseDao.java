package com.miteno.base;

import java.util.List;

import com.miteno.util.PageView;

/**
 * 集合持久层的公用的增，删，改，查接口
 * <T> 表示传入实体类
 * @author yifeng
 * 2015-04-01
 * @Email:  
 * @version 1.0v
 * @param <T>
 */
public interface BaseDao<T> {
	/**
	 * 返回分页后的数据
	 * @param pageView
	 * @param t
	 * @return
	 */
	public List<T> query(PageView pageView,T t);
	/**
	 * 返回所有数据
	 * @param t
	 * @return
	 */
	public List<T> queryAll(T t);
	public void delete(String id);
	public void modify(T t);
	public T getById(String id);
	public void add(T t);
}
