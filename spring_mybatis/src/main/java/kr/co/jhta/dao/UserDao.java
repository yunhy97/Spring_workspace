package kr.co.jhta.dao;

import java.util.List;

import kr.co.jhta.vo.User;

public interface UserDao {

	/**
	 * 지정된 아이디에 해당하는 사용자정보를 반환한다.
	 * @param userId 조회할 사용자 아이디
	 * @return 사용자정보가 포함된 User객체, null반환될 수 있음
	 */
	User getUserById(String userId);
	
	List<User> getUsersByName(String name);
	
	void deleteUser(String id);
	
	void updateUser(User user);
	
	/**
	 * 지정된 사용자 정보를 저장한다.
	 * @param user 사용자 정보가 포함된 User객체
	 */
	void insertUser(User user);
	
}
