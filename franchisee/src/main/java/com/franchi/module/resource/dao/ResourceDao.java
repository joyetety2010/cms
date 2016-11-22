package com.franchi.module.resource.dao;

import org.springframework.stereotype.Repository;

import com.franchi.module.pojo.resource.Resource;
import com.unioncast.db.HBaseDao;


@Repository("resourceDao")
public class ResourceDao extends HBaseDao<Resource> implements IResourceDao {

	@Override
	public void addResource(Resource res) {
		insert(res);
	}

	@Override
	public void updateResource(Resource res) {
		update(res);
	}

	@Override
	public void deleteResource(int id) {
		update("Resource FROM User WHERE Id IN ("+id+")");
	}

}
