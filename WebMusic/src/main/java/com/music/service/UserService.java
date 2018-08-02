package com.music.service;

import com.music.entity.User;

public interface UserService {
	public User getByUserName(String userName);
	public User findByNickName(String nickName);
	public Integer addUser(User user);
	public User login(User user);
}
