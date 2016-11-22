package com.franchi.module.controller;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.franchi.module.user.service.IUserService;


@Controller
@RequestMapping("/")
public class LoginController {

	@Resource(name = "userService")
	private IUserService userService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(String userName, String passWord, Model model) {
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(userName,
				passWord);
		String emsg = null;
		try {
			subject.login(token);
		} catch (UnknownAccountException e) {
			emsg = e.getMessage();
		} catch (IncorrectCredentialsException e) {
			emsg = e.getMessage();
		} catch (LockedAccountException e) {
			emsg = "账号已经被锁定，请与管理员联系！";
		} catch (AuthenticationException e) {
			e.printStackTrace();
			emsg = "其他异常：" + e.getMessage();
		}
		if (emsg == null) {
			return "redirect:/admin/index";
		} else {
			model.addAttribute("emsg", emsg);
			return "login";
		}
	}
}
