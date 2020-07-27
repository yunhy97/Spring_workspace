package kr.co.jhta.di.service.step7;

import java.util.List;

import kr.co.jhta.di.service.MessageSender;
import kr.co.jhta.di.vo.User;

public class EventNotificationServiceImpl implements EventNotificationService{

	private MessageSender messageSender;
	private UserService userService;
	
	public void setMessageSender(MessageSender messageSender) {
		this.messageSender = messageSender;
	}
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@Override
	public void noticeEvent(String eventName, String eventContent) {
		List<User> users = userService.getAllUser();
		
		for(User user : users) {
			messageSender.send("**팀", user.getTel(), eventName, eventContent);
		}
	}
}
