package com.music.dao;


import com.music.entity.User;

public interface UserDao {
	public User findByUserName(String userName);
	public User findByNickName(String nickName);
	public Integer addUser(User user);
	public User login(User user);
}
