package kr.co.jhta.di.step1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import kr.co.jhta.di.SmsMessageSender;

public class Main {

	public static void main(String[] args) {
		String conf = "classpath:/spring/context-step1.xml";
		
		ApplicationContext ctx = new GenericXmlApplicationContext(conf);
		
		SmsMessageSender sms = ctx.getBean(SmsMessageSender.class);
		
		sms.send("**부", "010-1111-1111", "창고대방출", "7/31까지 할인");
		
		EventNotificationServiceImpl event = ctx.getBean(EventNotificationServiceImpl.class);
		event.notice("**팀", "이벤트", "시작");
	}
}
