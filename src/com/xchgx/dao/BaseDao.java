package com.xchgx.dao;


import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

public class BaseDao<T> {
	
	private Class<T> entityClass;
	@Autowired
	private SessionFactory sessionFactory;
	/**
	 * 通过反射获取子类确定的泛型类
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public BaseDao() {
		Type genType = getClass().getGenericSuperclass();
		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
		entityClass = (Class) params[0];
	}

	/**
	 * 根据ID加载PO实例
	 * 
	 * @param id
	 * @return 返回相应的持久化PO实例
	 */
	@SuppressWarnings("unchecked")
	public T load(Serializable id) {
		return (T) getCurrentSession().load(entityClass, id);
		
	}

	/**
	 * 根据ID获取PO实例
	 * 
	 * @param id
	 * @return 返回相应的持久化PO实例
	 */
	@SuppressWarnings("unchecked")
	public T get(Serializable id) {
		return (T) getCurrentSession().get(entityClass, id);
	}
	
//	/**
//	 * 获取PO的所有对象
//	 * 
//	 * @return
//	 */
//	public List<T> loadAll() {
//		return getCurrentSession().loadAll(entityClass);
//	}
	
	/**
	 * 保存PO
	 * 
	 * @param entity
	 */
	public void save(T entity) {
		getCurrentSession().saveOrUpdate(entity);
	}
	public void merge(T entity){
		getCurrentSession().merge(entity);
	}
	public void persist(T entity){
		getCurrentSession().persist(entity);
	}
	/**
	 * 添加一个PO
	 * @param entity
	 */
	public void add(T entity){
		getCurrentSession().save(entity);
	}
	/**
	 * 删除PO
	 * 
	 * @param entity
	 */
	public void remove(T entity) {
		Object obj = this.getCurrentSession().merge(entity);
		getCurrentSession().delete(obj);
	}
	
	/**
	 * 清空查询数据
	 */
	@SuppressWarnings("unchecked")
	public void removeAll(String hql){
		List<T> list = find(hql);
		for(T entity : list){
			remove(entity);
		}
	}

	/**
	 * 更改PO
	 * 
	 * @param entity
	 */
	public void update(T entity) {
		getCurrentSession().update(entity);
	}

	/**
	 * 执行HQL查询
	 * 
	 * @param sql
	 * @return 查询结果
	 */
	@SuppressWarnings("rawtypes")
	public List find(String hql) {
		return getCurrentSession().createQuery(hql).list();
	}

	/**
	 * 执行带参的HQL查询
	 * 
	 * @param sql
	 * @param params
	 * @return 查询结果
	 */
	@SuppressWarnings("rawtypes")
	public List find(String hql, Object... params) {
		Query query = getCurrentSession().createQuery(hql);
		for (int i = 0; i < params.length; i++) {
			query.setParameter(i, params[i]);
		}
		return query.list();
	}
	
	/**
	 * 查询最新的size条记录
	 * @param hql
	 * @param size
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List find(String hql, Integer size, Object... params) {
		Query query = getCurrentSession().createQuery(hql);
		for (int i = 0; i < params.length; i++) {
			query.setParameter(i, params[i]);
		}
		return query.setFirstResult(0).setMaxResults(size).list();
	}
//    
//	/**
//	 * 对延迟加载的实体PO执行初始化
//	 * @param entity
//	 */
//	public void initialize(Object entity) {
//		this.getCurrentSession().initialize(entity);
//	}
	
	
	/**
	 * 分页查询函数，使用hql.
	 *
	 * @param pageNo 页号,从1开始.
	 */
	@SuppressWarnings("rawtypes")
	synchronized public Page pagedQuery(String hql, int pageNo, int pageSize, Object... values) {
		Assert.hasText(hql);
		Assert.isTrue(pageNo >= 1, "pageNo should start from 1");
		// Count查询
		String countQueryString = " select count (*) " + removeSelect(removeOrders(hql));
		List countlist = find(countQueryString, values);
		long totalCount = (Long) countlist.get(0);

		if (totalCount < 1)
			return new Page();
		// 实际查询返回分页对象
		int startIndex = Page.getStartOfPage(pageNo, pageSize);
		Query query = createQuery(hql, values);
		List list = query.setFirstResult(startIndex).setMaxResults(pageSize).list();

		return new Page(startIndex, totalCount, pageSize, list);
	}

	/**
	 * 创建Query对象. 对于需要first,max,fetchsize,cache,cacheRegion等诸多设置的函数,可以在返回Query后自行设置.
	 * 留意可以连续设置,如下：
	 * <pre>
	 * dao.getQuery(hql).setMaxResult(100).setCacheable(true).list();
	 * </pre>
	 * 调用方式如下：
	 * <pre>
	 *        dao.createQuery(hql)
	 *        dao.createQuery(hql,arg0);
	 *        dao.createQuery(hql,arg0,arg1);
	 *        dao.createQuery(hql,new Object[arg0,arg1,arg2])
	 * </pre>
	 *
	 * @param values 可变参数.
	 */
	public Query createQuery(String hql, Object... values) {
		Assert.hasText(hql);
		Query query = queryCurrentSession().createQuery(hql);
		for (int i = 0; i < values.length; i++) {
			query.setParameter(i, values[i]);
		}
		return query;
	}
	/**
	 * 去除hql的select 子句，未考虑union的情况,用于pagedQuery.
	 *
	 * @see #pagedQuery(String,int,int,Object[])
	 */
	private static String removeSelect(String hql) {
		Assert.hasText(hql);
		int beginPos = hql.toLowerCase().indexOf("from");
		Assert.isTrue(beginPos != -1, " hql : " + hql + " must has a keyword 'from'");
		return hql.substring(beginPos);
	}
	
	/**
	 * 去除hql的orderby 子句，用于pagedQuery.
	 *
	 * @see #pagedQuery(String,int,int,Object[])
	 */
	private static String removeOrders(String hql) {
		Assert.hasText(hql);
		Pattern p = Pattern.compile("order\\s*by[\\w|\\W|\\s|\\S]*", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(hql);
		StringBuffer sb = new StringBuffer();
		while (m.find()) {
			m.appendReplacement(sb, "");
		}
		m.appendTail(sb);
		return sb.toString();
	}

    public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * 执行增删改查的session
	 * @return
	 */
	public  Session getCurrentSession() {
        //return SessionFactoryUtils.getSession(hibernateTemplate.getSessionFactory(),true);
		return null==sessionFactory.getCurrentSession()?sessionFactory.openSession():sessionFactory.getCurrentSession();
    }
	/**
	 * 执行查询的session
	 * @return
	 */
	public Session queryCurrentSession(){
		return sessionFactory.openSession();
	}

}
