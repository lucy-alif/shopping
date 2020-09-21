package com.example.demo.dao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.pojo.Userinfo;
import com.example.demo.util.MD5;

@SpringBootTest
class UserinfoMapperTest {
	@Autowired UserinfoMapper userinfoMapper;

	@Test
	void testValidateLogin() throws Exception{
		Userinfo userinfo=userinfoMapper.validateLogin("zhangsan", MD5.enctypeMD5("haha123456"));
		assertNotNull(userinfo);
		System.out.println(userinfo);
	}

}
