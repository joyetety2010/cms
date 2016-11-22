package com.franchi.module.resource.dao;


import com.franchi.module.pojo.resource.Resource;



public interface IResourceDao {

	public void addResource(Resource res);

	public void updateResource(Resource res);

	public void deleteResource(int id);

}
