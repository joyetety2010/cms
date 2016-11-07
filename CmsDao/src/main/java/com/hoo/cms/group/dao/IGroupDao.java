package com.hoo.cms.group.dao;

import java.util.List;

import com.hoo.basic.model.Pager;
import com.hoo.cms.pojo.user.Group;

public interface IGroupDao {

	public List<Group> selectGroup();
	
	public Pager<Group> findGroup();
	
	public void deleteGroupUsers(int gid);
	
	public void addGroup(Group group);

	public void deleteGroup(int id);

	public Group loadGroup(int id);

	public void updateGroup(Group group);
}
