package com.example.demo.dao;


import java.util.List;

import com.example.demo.pojo.Student;

public interface StudentMapper {
    int deleteByPrimaryKey(Integer stuId);

    int insert(Student record);

    int insertSelective(Student record);

    Student selectByPrimaryKey(Integer stuId);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);
    
    /**
     * 查询学生的id、学号、姓名、余额、并隐藏id
     */
    List<Student> shwoAllStudent();
    
    /**
     * 根据姓名查询同名学生的姓名最大后缀
     * @param name 学生姓名
     * @return
     */
    String getSameNameMaxName(String name);
	
    /**
     * 根据月份查询当月学生的最大编号
     * @param prefix 学生编号的年月日部分
     * @return
     */
	String queryMaxStuNoByMonth(String prefix);
}