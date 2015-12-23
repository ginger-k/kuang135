package com.kuang135.demo.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import tk.mybatis.mapper.common.Mapper;

import com.kuang135.demo.pojo.User;



@Repository
public interface UserMapper extends Mapper<User> {
	
	List<User> getAll();

}
