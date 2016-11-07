package com.hoo.cms.group.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.hoo.basic.model.Pager;
import com.hoo.cms.exception.CmsException;
import com.hoo.cms.group.dao.IGroupDao;
import com.hoo.cms.pojo.user.Group;
import com.hoo.cms.pojo.user.User;
import com.hoo.cms.user.dao.IUserDao;

@Service("groupService")
public class GroupService implements IGroupService{

	@Inject
	private IGroupDao groupDao;
	
	@Inject
	private IUserDao userDao;
	

	@Override
	public void add(Group group) {
		groupDao.addGroup(group);
	}

	@Override
	public void delete(int id) {
		List<User> users = userDao.listGroupUsers(id);
		if(users!=null&&users.size()>0) throw new CmsException("删除的组中还有用户，不能删除");
		groupDao.deleteGroup(id);
	}

	@Override
	public Group load(int id) {
		return groupDao.loadGroup(id);
	}

	@Override
	public void update(Group group) {
		groupDao.updateGroup(group);
	}

	@Override
	public List<Group> listGroup() {
		return groupDao.selectGroup();
	}

	@Override
	public Pager<Group> findGroup() {
		return groupDao.findGroup();
	}

	@Override
	public void deleteGroupUsers(int gid) {
		groupDao.deleteGroupUsers(gid);
	}

}
