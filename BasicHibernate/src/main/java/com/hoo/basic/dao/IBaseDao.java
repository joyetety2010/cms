package com.hoo.basic.dao;

public interface IBaseDao<T> {

	public T add(T t);
	
	public T load(int id);
	
	public void update(T t);
	
	public void delete(int id);
	
}
