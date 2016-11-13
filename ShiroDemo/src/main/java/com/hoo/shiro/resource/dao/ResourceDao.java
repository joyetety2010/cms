package com.hoo.shiro.resource.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hoo.basic.dao.BaseDao;
import com.hoo.shiro.resource.pojo.Resource;

@Repository("resourceDao")
public class ResourceDao extends BaseDao<Resource> implements IResourceDao {

	@Override
	public List<Resource> listResource() {
		return super.list("from Resource");
	}

	@Override
	public void addResource(Resource res) {
		this.add(res);
	}

	@Override
	public void updateResource(Resource res) {
		this.update(res);
	}

	@Override
	public void deleteResource(int id) {
		this.delete(id);
	}

	@Override
	public Resource loadResource(int id) {
		return this.load(id);
	}

}
