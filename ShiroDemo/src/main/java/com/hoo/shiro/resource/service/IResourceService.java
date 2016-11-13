package com.hoo.shiro.resource.service;

import java.util.List;

import com.hoo.shiro.resource.pojo.Resource;


public interface IResourceService {
	public void add(Resource res);
	
	public void update(Resource res);
	
	public void delete(int id);
	
	public Resource load(int id);
	
	public List<Resource> listResource();
}
