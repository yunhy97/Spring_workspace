package kr.co.jhta.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.co.jhta.util.MybatisUtil;
import kr.co.jhta.vo.User;

public class UserDao {

	//insert: N종류의 데이터, parameter:User
	public void insertUser(User user) {
		SqlSession session = MybatisUtil.getSqlSession();
		try {
			session.insert("insertUser", user);
			session.commit();
		} finally {
			session.close();
		}
	}
	
	//delete: 없음, parameter:없음
	public void deleteAllUsers() {
		SqlSession session = MybatisUtil.getSqlSession();
		try {
			session.delete("deleteAllUsers");
			session.commit();
		} finally {
			session.close();
		}
	}
	
	//delete: 기본자료형(String 포함), parameter:userId
	public void deleteUserById(String userId) {
		SqlSession session = MybatisUtil.getSqlSession();
		try {
			session.delete("deleteUserById", userId);
			session.commit();
		} finally {
			session.close();
		}
	}
	
	//update: N종류의 데이터, parameter:User
	public void updateUser(User user) {
		SqlSession session = MybatisUtil.getSqlSession();
		try {
			session.update("updateUser",user);
			session.commit();
		} finally {
			session.close();
		}
	}
	
	//select: N행 N열, parameter:없음, result:User, 최종값:List<User>
	public List<User> getAllUsers() {
		SqlSession session = MybatisUtil.getSqlSession();
		try {
			List<User> users =  session.selectList("getAllUeser");
			return users;
		} finally {
			session.close();
		}
	}
	
	//select: N행 N열, parameter:String, result:User, 최종값:List<User> 
	public List<User> getUsersByName(String userName) {
		return null;
	}
	
	//select: 1행 N열, parameter:String, result:User 최종값:User객체
	public User getUserById(String userId) {
		SqlSession session = MybatisUtil.getSqlSession();
		try {
			User user = session.selectOne("getUserById",userId);
			return user;
		} finally {
			session.close();
		}
	}
	
	//select: 1행 N열, parameter:String, result:User, 최종값:User객체
	public User getUserByEmail(String userEmail) {
		return null;
	}
	
	//select: 1행 1열, parameter:없음, result:int, 최종값:int
	public int getUsersCount() {
		SqlSession session = MybatisUtil.getSqlSession();
		try {
			int usersCount = session.selectOne("getUsersCount");
			return usersCount;
		} finally {
			session.close();
		}
	}
}
