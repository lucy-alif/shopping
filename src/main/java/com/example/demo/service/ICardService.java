package com.example.demo.service;

import java.util.List;
import com.example.demo.pojo.College;
import com.example.demo.pojo.Student;
import com.example.demo.pojo.Userinfo;

public interface ICardService {
	/*-----------------------userinfo开始------------------------------------------*/
	//登录验证
	 Userinfo validateLogin(String userLogin,String userPasswd); 
	/*-----------------------userinfo结束------------------------------------------*/
	 
	/*-----------------------student开始------------------------------------------*/
	 //查询所有学生
	 List<Student> shwoAllStudent();
	 
	 //根据id查询单条学生信息
	 Student selectStudentBystuId(Integer stuId);
	 
	 //修改学生信息
	 boolean updateStudent(Student student);
	 
	 //新增一条学生信息
	 boolean addStudent(Student student);
	/*-----------------------student结束------------------------------------------*/
	 
	 
	 
	 /*-----------------------college开始------------------------------------------*/
	 
	 //查询所有college
	 List<College>  showAllColleges();
	 /*-----------------------college结束------------------------------------------*/
}
