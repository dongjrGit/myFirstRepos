package com.music.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.music.dao.UserDao;
import com.music.entity.User;
import com.music.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;
	
	public User getByUserName(String userName) {
		return userDao.findByUserName(userName);
	}

	public User findByNickName(String nickName) {
		return userDao.findByNickName(nickName);
	}
	@Transactional
	public Integer addUser(User user) {
		return userDao.addUser(user);
	}

	public User login(User user){
		return userDao.login(user);
	}

}
