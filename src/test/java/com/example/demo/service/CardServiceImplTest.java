package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.pojo.Student;
import com.example.demo.pojo.Userinfo;

@SpringBootTest
class CardServiceImplTest {
	@Autowired 
	private ICardService cardService;
	
	@Test
	void testValidateLogin() throws Exception{
		Userinfo userinfo=cardService.validateLogin("zhangsan", "123456");
		System.out.println(userinfo);
		assertNotNull(userinfo);
	}
	
	@Test
	void testShowAllStudent() throws Exception{
		List<Student> students=cardService.shwoAllStudent();
		assertTrue(students.size()>0);
		for (Student student : students) {
			System.out.println(student);
		}
	}

}
