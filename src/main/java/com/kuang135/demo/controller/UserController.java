package com.kuang135.demo.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kuang135.demo.pojo.User;
import com.kuang135.demo.service.UserService;
import com.kuang135.frame.dao.DbUtil;


/*
 * test
 */
@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/demo/jsp")
	public String jsp(){
		System.out.println("controller demo --- --- ");
		return "demo/demo";
	}
	
	
	@RequestMapping(value="/demo/dbutil")
	@ResponseBody
	public List<Map<String, Object>> dbutil() throws SQLException{
		System.out.println("controller demo dbutil --- --- ");
		String sql = "SELECT id id,username name,password password FROM user";
		return DbUtil.getMapList(sql);
	}
	
	@RequestMapping(value="/demo/commonMapper")
	@ResponseBody
	public List<User> commonMapper() throws SQLException{
		System.out.println("controller demo common_mapper --- --- ");
		return userService.selectAll();
	}
	
	@RequestMapping(value="/demo/mybatis")
	@ResponseBody
	public List<User> mybatis() throws SQLException{
		System.out.println("controller demo mybatis --- --- ");
		return userService.getAll();
	}
	
	

}
