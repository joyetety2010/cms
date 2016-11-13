package com.hoo.shiro.resource.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.hoo.shiro.resource.dao.IResourceDao;
import com.hoo.shiro.resource.pojo.Resource;

@Service("resourceService")
public class ResourceService implements IResourceService {
	@Inject
	private IResourceDao resourceDao;
	public void add(Resource res) {
		resourceDao.addResource(res);
	}

	public void update(Resource res) {
		resourceDao.updateResource(res);
	}

	public void delete(int id) {
		resourceDao.deleteResource(id);
	}

	public Resource load(int id) {
		return resourceDao.loadResource(id);
	}

	public List<Resource> listResource() {
		return resourceDao.listResource();
	}
}
