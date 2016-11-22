package com.franchi.module.user.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.franchi.module.pojo.page.Pages;
import com.franchi.module.pojo.user.User;
import com.franchi.module.pojo.user.UserDTO;
import com.franchi.module.pojo.user.UserRole;
import com.franchi.module.user.service.IUserService;

@RequestMapping("/admin")
@Controller
public class UserController {

	@javax.annotation.Resource(name = "userService")
	private IUserService userService;

	@RequestMapping(value = "index", method = RequestMethod.GET)
	public String list(Model model) {
		return "loginsuccess";
	}

	/**
	 * 用户列表
	 * 
	 * @param req
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/user/list", method = RequestMethod.GET)
	public String userList(HttpServletRequest req, Model model) {
		Subject subject = SecurityUtils.getSubject();

		int currPage = NumberUtils.toInt(req.getParameter("curr"), 1);
		int offset = NumberUtils.toInt(req.getParameter("nOffset"), 10);
		int nStart = (currPage - 1) * offset;
		String userName = StringUtils.defaultIfEmpty(req.getParameter("txt"),
				null);
		Pages<UserDTO> page = new Pages<UserDTO>();
		List<UserDTO> userList = userService.listUser(userName, nStart, offset);
		page.setData(userList);
		page.setTotal(userService.getUser(userName));
		page.setOffset(offset);
		page.setCurrPage(currPage);
		model.addAttribute("userList", userList);
		model.addAttribute("userName",
				((User) subject.getPrincipal()).getUserName());
		model.addAttribute("page", page);

		String path;
		if ("search".equals(StringUtils.defaultIfEmpty(
				req.getParameter("search"), null))) {
			path = "/user/ajaxlist";
		} else {
			model.addAttribute("type", "user");
			path = "/user/list";
		}

		return path;
	}

	/**
	 * 更新用户状态
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/user/update", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> update(HttpServletRequest req) {
		int userId = NumberUtils.toInt(req.getParameter("userId"), 0);
		int status = NumberUtils.toInt(req.getParameter("status"), 0);
		Map<String, Object> m = new HashMap<String, Object>();
		if (userService.updateStatus(userId, status) > 0) {
			m.put("message", "成功");
		} else
			m.put("message", "失败");
		return m;
	}

	/**
	 * 修改用户角色
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/user/changeRole", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> changeRole(HttpServletRequest req) {
		int userId = NumberUtils.toInt(req.getParameter("userId"), 0);
		int roleId = NumberUtils.toInt(req.getParameter("roleId"), 0);
		int check = NumberUtils.toInt(req.getParameter("check"), -1);
		Map<String, Object> m = new HashMap<String, Object>();
		UserRole ur = new UserRole();
		ur.setRoleId(roleId);
		ur.setUserId(userId);
		try {
			if (check == 1) {
				userService.addUserRole(ur);
			} else if (check == 0) {
				if (userService.listUserRole(userId).size() == 1) {
					m.put("success", false);
					m.put("message", "用户至少存在一个角色！");
					return m;
				}
				userService.deleteUserRole(ur);
			}
		} catch (Exception e) {
			m.put("success", false);
			m.put("message", "修改失败！");
		}
		m.put("success", true);
		return m;
	}

	/**
	 * 删除用户
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/user/delete", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> deleteUser(HttpServletRequest req) {
		int userId = NumberUtils.toInt(req.getParameter("userId"), 0);
		Map<String, Object> m = new HashMap<String, Object>();
		try {
			if (userService.deleteUser(userId) > 0) {
				m.put("success", true);
			}

		} catch (Exception e) {
			m.put("success", false);
			m.put("message", e.getMessage());
		}
		return m;
	}

	/**
	 * 添加用户
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/user/add", method = RequestMethod.GET)
	public String addUser(Model model) {
		model.addAttribute("type", "user");
		return "/user/adduser";
	}

	@RequestMapping(value = "/user/add", method = RequestMethod.POST)
	public String addUser(HttpServletRequest req, Model model) {
		String userName = req.getParameter("userName");
		String passWord = req.getParameter("passWord");
		String nickName = req.getParameter("nickName");
		String[] roles = req.getParameterValues("docVlCb");

		User user = new User();
		user.setUserName(userName);
		user.setNickName(nickName);
		user.setPassWord(passWord);
		user.setStatus(1);
		user.setCreateTime(new Date());

		int[] intTemp = new int[roles.length];
		for (int i = 0; i < roles.length; i++) {
			intTemp[i] = Integer.parseInt(roles[i]);
		}

		userService.addUser(user, intTemp);
		return "redirect:/admin/user/list";
	}

	/**
	 * 检测用户是否已存在
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/user/checkUser", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> checkUser(HttpServletRequest req) {
		String userName = req.getParameter("userName");
		Map<String, Object> m = new HashMap<String, Object>();
		if (userService.checkUser(userName)) {
			m.put("success", true);
		} else
			m.put("success", false);
		return m;
	}

	@RequestMapping(value = "/checkPswd", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> checkPswd(HttpServletRequest req) {
		String passWord = req.getParameter("passWord");
		Subject subject = SecurityUtils.getSubject();
		User currUser = (User) subject.getPrincipal();
		Map<String, Object> m = new HashMap<String, Object>();
		if (userService.checkPswd(currUser.getId(), passWord)) {
			m.put("success", true);
		} else
			m.put("success", false);
		return m;
	}

	@RequestMapping(value = "/editPswd", method = RequestMethod.POST)
	public String editPswd(HttpServletRequest req) {
		String passWord = req.getParameter("passWord");
		String oldPsd = req.getParameter("oldPsd");
		Subject subject = SecurityUtils.getSubject();
		User currUser = (User) subject.getPrincipal();
		userService.editPswd(currUser.getId(), passWord, oldPsd);
		return "loginsuccess";
	}

	@RequestMapping(value = "/editPswd", method = RequestMethod.GET)
	public String editPswd(Model model) {
		model.addAttribute("type", "pswd");
		return "/pswd/pswd";
	}
}
