package com.hoo.cms.group.service;

import java.util.List;

import com.hoo.basic.model.Pager;
import com.hoo.cms.pojo.user.Group;

public interface IGroupService {
	public void add(Group group);
	public void delete(int id);
	public Group load(int id);
	public void update(Group group);
	
	public List<Group> listGroup();
	public Pager<Group> findGroup();
	public void deleteGroupUsers(int gid);
}
