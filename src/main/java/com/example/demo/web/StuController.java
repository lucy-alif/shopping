package com.example.demo.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.pojo.Student;
import com.example.demo.service.ICardService;
import com.example.demo.vo.StuPage;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@RestController /*@Controller和@ResponseBody的合体*/
@RequestMapping("/card")
@CrossOrigin (allowCredentials="true", allowedHeaders="*")//支持跨域访问
public class StuController {
	@Autowired
	private ICardService cardService;

	//查询所有学生信息
	@RequestMapping(path = "/showStudents",method = {RequestMethod.POST,RequestMethod.GET})
	public PageInfo<Student> showAllStudents(@RequestBody StuPage page) {
		PageHelper.startPage(page.getPageNum(),page.getPageSize());
		List<Student> students=cardService.shwoAllStudent();
		/*浏览器输入以下地址测试一下数据
		 * http://localhost:8888/card//showStudents
		 */		
		PageInfo<Student> info=new PageInfo<Student>(students);
		return info;
	}
	
	//根据前端发送的id查询对应学生信息
	@RequestMapping(path = "/selectStudent",method = {RequestMethod.POST,RequestMethod.GET})
	public Student selectOneStudentById(Integer stuId) {
		Student student=cardService.selectStudentBystuId(stuId);
			
		return student;
	}	
	
	//根据前端发送的信息对学生信息进行修改或新增
	@RequestMapping(path = "/saveStudent",method = {RequestMethod.POST,RequestMethod.GET})
	public boolean saveStudent(@RequestBody Student student) {
		boolean result=false;
		if (student.getStuId()!=null) {//说明是修改
			result=cardService.updateStudent(student);
		}else {
			result=cardService.addStudent(student);
		}
			
		return result;
	}

}
