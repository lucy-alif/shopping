package com.example.demo.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.pojo.Userinfo;
import com.example.demo.service.ICardService;

@RestController /*@Controller和@ResponseBody的合体*/
@RequestMapping("/card")
@CrossOrigin (allowCredentials="true", allowedHeaders="*")//支持跨域访问
public class UserController {
	@Autowired
	private ICardService cardService;
	
	//登录方法
	@RequestMapping(path = "/login",method = {RequestMethod.POST,RequestMethod.GET})
	public boolean loginByUserinfo(@RequestBody Userinfo userinfo,HttpSession session) {
		boolean result=false;
		Userinfo user=cardService.validateLogin(userinfo.getUserLogin(), userinfo.getUserPasswd());
		if (user!=null) {
			session.setAttribute("user", user);/*登陆成功将登录对象放进session，但前端无法从session中取到这个对象
			 									只能发送请求给后端控制器，由后端控制器去session中获取信息返回给前端*/
			result=true;
		}
		return result;
	}
	
	//获取登录用户信息传递给前端页面
	@RequestMapping(path = "/username",method = {RequestMethod.POST,RequestMethod.GET})
	public String getUserName(HttpSession session) {
		Userinfo userinfo=(Userinfo)session.getAttribute("user");
		if (userinfo!=null) {
			return userinfo.getUserName();
		}
		return null;/*如果没有取到登录用户对象就返回null*/
	}
}
