package kr.co.jhta.di.service.step7;

import java.util.ArrayList;
import java.util.List;

import kr.co.jhta.di.vo.User;

public class UserServiceImpl implements UserService {
	
	
	@Override
	public List<User> getAllUser() {
		List<User> users = new ArrayList<>();
		
		User user1 = new User("hong","홍길동","010-1111-1111", "sms");
		User user2 = new User("kang","강감찬","010-2222-2222", "kakaoTalk");
		User user3 = new User("ryu","류관순","010-3333-3333", "kakaoTalk");
		
		
		users.add(user1);
		users.add(user2);
		users.add(user3);
		
		return users;
	}
}
