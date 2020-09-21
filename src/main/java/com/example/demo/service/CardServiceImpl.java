package com.example.demo.service;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.CollegeMapper;
import com.example.demo.dao.StudentMapper;
import com.example.demo.dao.UserinfoMapper;
import com.example.demo.pojo.College;
import com.example.demo.pojo.Student;
import com.example.demo.pojo.Userinfo;
import com.example.demo.util.MD5;

@Service
public class CardServiceImpl implements ICardService{
	@Autowired
	private UserinfoMapper userinfoMapper;
	@Autowired
	private StudentMapper studentMapper;
	@Autowired
	private CollegeMapper collegeMapper;
	
	/*-----------------------userinfo开始------------------------------------------*/
	@Override
	@Transactional(propagation =Propagation.SUPPORTS,isolation =Isolation.DEFAULT,readOnly = true)
	public Userinfo validateLogin(String userLogin, String userPasswd) {
		
		return userinfoMapper.validateLogin(userLogin, MD5.enctypeMD5("haha"+userPasswd));
	}
	/*-----------------------userinfo结束------------------------------------------*/

	
	/*-----------------------student开始------------------------------------------*/

	@Override
	@Transactional(propagation =Propagation.SUPPORTS,isolation =Isolation.DEFAULT,readOnly = true)
	public List<Student> shwoAllStudent() {
		
		return studentMapper.shwoAllStudent();
	}


	@Override
	@Transactional(propagation =Propagation.SUPPORTS,isolation =Isolation.DEFAULT,readOnly = true)
	public Student selectStudentBystuId(Integer stuId) {
		
		return studentMapper.selectByPrimaryKey(stuId);
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,rollbackFor = Exception.class)
	public boolean updateStudent(Student student) {
		
		return studentMapper.updateByPrimaryKeySelective(student)>0;
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,rollbackFor = Exception.class)
	public boolean addStudent(Student stu) {
		//学生编号总共12位，由字母STU+年月+3位数字编号组成（如STU201806001），必须唯一，自动生成，数字编号自动增长
		Calendar cal=Calendar.getInstance();
		int year=cal.get(Calendar.YEAR);
		int month=cal.get(Calendar.MONTH)+1;
		String monthStr=("0"+month).substring(month>=10?1:0);
		String prefix="STU"+year+monthStr;//结果:STU202008
		String maxStuNo=studentMapper.queryMaxStuNoByMonth(prefix+"%");
		if(maxStuNo==null){//是本月的第1个
			stu.setStuNo(prefix+"001");
		}else{//不是第1个
			String newNo=Integer.parseInt(maxStuNo.substring(maxStuNo.length()-3))+1+"";
			String stuNo=prefix+("00"+newNo).substring(newNo.length()-1);
			stu.setStuNo(stuNo);
		}
		//处理姓名
		String maxName=studentMapper.getSameNameMaxName("^"+stu.getStuName()+"[1-9]{0,1}$");
		if(maxName!=null){//存在同名的情况
			String newName=stu.getStuName()+(Integer.parseInt(maxName.replace(stu.getStuName(), "0"))+1);
			stu.setStuName(newName);
		}
		
		return studentMapper.insertSelective(stu)>0;
	}
	
	/*-----------------------student结束------------------------------------------*/
	
	

	/*-----------------------college开始------------------------------------------*/
	@Override
	@Transactional(propagation =Propagation.SUPPORTS,isolation =Isolation.DEFAULT,readOnly = true)
	public List<College> showAllColleges() {
		 
		return collegeMapper.showAllColleges();
	}


	


	

	/*-----------------------college结束------------------------------------------*/
	

}
