package com.hoo.cms.group.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hoo.basic.dao.BaseDao;
import com.hoo.basic.model.Pager;
import com.hoo.cms.pojo.user.Group;

@Repository("groupDao")
public class GroupDao extends BaseDao<Group> implements IGroupDao {

	@Override
	public List<Group> selectGroup() {
		String hql = " from Group";
		return this.list(hql);
	}

	@Override
	public Pager<Group> findGroup() {
		String hql = " from Group";
		return this.find(hql);
	}

	@Override
	public void deleteGroupUsers(int gid) {
		this.updateByHql("delete UserGroup ug where ug.group.id=?",gid);
	}

	@Override
	public void addGroup(Group group) {
		this.add(group);
	}

	@Override
	public void deleteGroup(int id) {
		this.delete(id);
	}

	@Override
	public Group loadGroup(int id) {
		return this.load(id);
	}

	@Override
	public void updateGroup(Group group) {
		this.update(group);
	}

}
