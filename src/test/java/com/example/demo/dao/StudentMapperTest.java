package com.example.demo.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.pojo.Student;

@SpringBootTest
class StudentMapperTest {
	@Autowired StudentMapper studentMapper;

	@Test
	void testShowAllStudent() throws Exception{
		List<Student> stuList=studentMapper.shwoAllStudent();
		assertTrue(stuList.size()>0);
		for (Student student : stuList) {
			System.out.println(student);
		}
	}

}
