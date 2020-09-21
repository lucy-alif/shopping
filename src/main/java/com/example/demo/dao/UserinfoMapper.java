package com.example.demo.dao;

import org.apache.ibatis.annotations.Param;

import com.example.demo.pojo.Userinfo;

public interface UserinfoMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(Userinfo record);

    int insertSelective(Userinfo record);

    Userinfo selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(Userinfo record);

    int updateByPrimaryKey(Userinfo record);
    /**
     * 登录验证
     */
    Userinfo validateLogin(@Param("userLogin")String userLogin, @Param("userPasswd")String userPasswd);
}