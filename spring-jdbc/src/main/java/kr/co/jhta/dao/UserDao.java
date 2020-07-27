package kr.co.jhta.dao;

import java.util.List;

import kr.co.jhta.vo.User;

public interface UserDao {
	public void insertUser(User user);
	
	public void deleteAllUsers();
	
	public void deleteUserById(String userId);
	
	public void updateUser(User user);
	
	public User getUserById(String userId);
	
	public List<User> getAllUsers();
	
	public List<User> getUsersByName(String username);
}
