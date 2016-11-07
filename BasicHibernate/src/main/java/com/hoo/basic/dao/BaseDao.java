package com.hoo.basic.dao;

import java.lang.reflect.ParameterizedType;
import java.math.BigInteger;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.hoo.basic.model.Pager;
import com.hoo.basic.model.SystemContext;

@SuppressWarnings({"unchecked","rawtypes","deprecation"})
public class BaseDao<T> implements IBaseDao<T> {

	private SessionFactory sessionFactory;
	
	private Class<?> clz;
	
	public Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Inject
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Class<?> getClz() {
		if(clz==null) {
			//获取泛型的Class对象
			clz = ((Class<?>)
					(((ParameterizedType)(this.getClass().getGenericSuperclass())).getActualTypeArguments()[0]));
		}
		return clz;
	}

	@Override
	public T add(T t) {
		getSession().save(t);
		return t;
	}

	
	@Override
	public T load(int id) {
		return (T)getSession().load(getClz(), id);
	}

	@Override
	public void update(T t) {
		getSession().update(t);
	}

	@Override
	public void delete(int id) {
		getSession().delete(this.load(id));
	}
	
	public List<T> list(String hql, Object[] args,Map<String, Object> alias){
		hql = initSort(hql);
		Query query = getSession().createQuery(hql);
		setAliasParameter(query,alias);
		setParameter(query,args,false);
		return query.list();
		
	}
	
	public List<T> list(String hql, Object[] args){
		return this.list(hql, args, null);
	}

	public List<T> list(String hql, Object arg){
		return this.list(hql, new Object[]{arg});
	}
	
	public List<T> list(String hql, Map<String, Object> alias){
		return this.list(hql, null, alias);
	}
	
	
	
	public List<T> list(String hql){
		return this.list(hql, null, null);
	}

	private void setParameter( Query query, Object[] args,Boolean isSQL) {
		if(args!=null&&args.length>0) {
			int index = 0;
			if(isSQL)
				index = 1;
			for(Object arg:args) {
				query.setParameter(index++, arg);
			}
		}
		
	}

	private void setAliasParameter(Query query, Map<String, Object> alias) {
		if(alias!=null) {
			Set<String> keys = alias.keySet();
			for(String key:keys) {
				Object val = alias.get(key);
				if(val instanceof Collection) {
					//查询条件是列表
					query.setParameterList(key, (Collection)val);
				} else {
					query.setParameter(key, val);
				}
			}
		}
	}

	private String initSort(String hql) {
		String order = SystemContext.getOrder();
		String sort = SystemContext.getSort();
		if(sort!=null&&!"".equals(sort.trim())) {
			hql+=" order by "+sort;
			if(!"desc".equals(order)) hql+=" asc";
			else hql+=" desc";
		}
		return hql;
	}
	
	
	public Pager<T> find(String hql, Object[] args, Map<String, Object> alias){
		hql = initSort(hql);
		String cq = getCountHql(hql, true);
		Query cquery = getSession().createQuery(cq);
		Query query = getSession().createQuery(hql);
		
		setAliasParameter(query, alias);
		setAliasParameter(cquery, alias);
		setParameter(query, args,false);
		setParameter(cquery, args, false);
		Pager<T> pagers = new Pager<T>();
		setPagers(query,pagers);
		List<T> datas = query.list();
		pagers.setDatas(datas);
		long total = (long)cquery.uniqueResult();
		pagers.setTotal(total);
		return pagers;
	}

	public Pager<T> find(String hql, Object[] args){
		return this.find(hql, args, null);
	}
	
	public Pager<T> find(String hql, Object arg){
		return this.find(hql, new Object[]{arg});
	}
	
	public Pager<T> find(String hql, Map<String, Object> alias){
		return this.find(hql, null, alias);
	}

	public Pager<T> find(String hql){
		return this.find(hql,null,null);
	}
	
	public Object queryObject(String hql, Object[] args, Map<String,Object> alias){
		Query query = getSession().createQuery(hql);
		setAliasParameter(query, alias);
		setParameter(query, args, false);
		return query.uniqueResult();
	}
	
	public Object queryObject(String hql, Object[] args){
		return this.queryObject(hql, args, null);
	}
	
	public Object queryObject(String hql, Object arg){
		return this.queryObject(hql, new Object[]{arg});
	}
	
	public Object queryObject(String hql, Map<String,Object> alias){
		return this.queryObject(hql, null, alias);
	}
	
	public Object querObject(String hql){
		return this.queryObject(hql, null, null);
	}
	
	private void setPagers(Query query, Pager pages) {
		Integer pageSize = SystemContext.getPageSize();
		Integer pageOffset = SystemContext.getPageOffset();
		if(pageOffset==null||pageOffset<0) pageOffset = 0;
		if(pageSize==null||pageSize<0) pageSize = 15;
		pages.setOffset(pageOffset);
		pages.setSize(pageSize);
		query.setFirstResult(pageOffset).setMaxResults(pageSize);
	}


	private String getCountHql(String hql, boolean b) {
		String e = hql.substring(hql.indexOf("from"));
		String c = "select count(*) "+e;
		if(b)
			c.replaceAll("fetch", "");
		return c;
	}
	
	public <N extends Object>List<N> listBySql(String sql, Object[] args, Class<?> clz) {
		return this.listBySql(sql, args, null, clz);
	}

	public <N extends Object>List<N> listBySql(String sql, Object arg, Class<?> clz) {
		return this.listBySql(sql, new Object[]{arg}, clz);
	}
	public <N extends Object>List<N> listBySql(String sql, Class<?> clz) {
		return this.listBySql(sql, null,null, clz);
	}

	public <N extends Object>List<N> listBySql(String sql, Map<String,Object> alias,Class<?> clz){
		return this.listBySql(sql, null, alias, clz);
	}
	
	public <N extends Object>List<N> listBySql(String sql, Object[] args,
			Map<String, Object> alias, Class<?> clz) {
		sql = initSort(sql);
		Query sq = getSession().createNativeQuery(sql,clz);
		setAliasParameter(sq, alias);
		setParameter(sq, args,true);
		return sq.list();
	}
	
	public <N extends Object>Pager<N> findBySql(String sql, Object[] args, Class<?> clz) {
		return this.findBySql(sql, args, null, clz);
	}

	public <N extends Object>Pager<N> findBySql(String sql, Object arg, Class<?> clz) {
		return this.findBySql(sql, new Object[]{arg}, clz);
	}

	public <N extends Object>Pager<N> findBySql(String sql, Class<?> clz) {
		return this.findBySql(sql, null, null,clz);
	}
	
	public <N extends Object>Pager<N> findBySql(String sql, 
			Map<String, Object> alias, Class<?> clz) {
		return this.findBySql(sql, null, alias,clz);
	}

	public <N extends Object>Pager<N> findBySql(String sql, Object[] args,
			Map<String, Object> alias, Class<?> clz) {
		sql = initSort(sql);
		String cq = getCountHql(sql,false);
		
		Query sq = getSession().createNativeQuery(sql,clz);
		Query cquery = getSession().createNativeQuery(cq);
		setAliasParameter(sq, alias);
		setAliasParameter(cquery, alias);
		setParameter(sq, args,true);
		setParameter(cquery, args,true);
		Pager<N> pages = new Pager<N>();
		setPagers(sq, pages);
		List<N> datas = sq.list();
		pages.setDatas(datas);
		long total = ((BigInteger)cquery.uniqueResult()).longValue();
		pages.setTotal(total);
		return pages;
	}

	public void updateByHql(String hql, Object[] args) {
		Query query = getSession().createQuery(hql);
		setParameter(query, args,false);
		query.executeUpdate();
	}

	public void updateByHql(String hql, Object arg) {
		this.updateByHql(hql,new Object[]{arg});
	}

	public void updateByHql(String hql) {
		this.updateByHql(hql,null);
	}


}
