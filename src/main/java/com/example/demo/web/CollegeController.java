package com.example.demo.web;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.pojo.College;
import com.example.demo.service.ICardService;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/card")
@CrossOrigin(allowCredentials="true", allowedHeaders="*")//支持跨域访问
public class CollegeController {
	
	@Autowired ICardService cardService;
	
	
	//查询所有college信息
	@RequestMapping(path = "/showColeges",method = {RequestMethod.POST,RequestMethod.GET})
	public List<College> showAllColleges() {
		List<College> colleges=cardService.showAllColleges();
		/*postman测一下
		 * http://localhost:8888/card/showColeges
		 */		
		return colleges;
	}

}
