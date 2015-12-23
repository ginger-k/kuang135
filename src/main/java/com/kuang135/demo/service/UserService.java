package com.kuang135.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kuang135.demo.mapper.UserMapper;
import com.kuang135.demo.pojo.User;
import com.kuang135.frame.service.BaseService;


@Service
public class UserService extends BaseService<User>{
	
	@Autowired
	private UserMapper userMapper;
	
	public List<User> getAll() {
		return userMapper.getAll();
	}

}
