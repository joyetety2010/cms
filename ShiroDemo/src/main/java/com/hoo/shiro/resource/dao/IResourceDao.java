package com.hoo.shiro.resource.dao;

import java.util.List;

import com.hoo.shiro.resource.pojo.Resource;


public interface IResourceDao {
	public List<Resource> listResource();

	public void addResource(Resource res);

	public void updateResource(Resource res);

	public void deleteResource(int id);

	public Resource loadResource(int id);
}
