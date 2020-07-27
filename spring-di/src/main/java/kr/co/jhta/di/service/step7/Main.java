package kr.co.jhta.di.service.step7;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Main {
	public static void main(String[] args) {
		String conf = "kr/co/jhta/di/service/step7/context-step7.xml";
		
		ApplicationContext ctx = new GenericXmlApplicationContext(conf);
		
		EventNotificationServiceImpl eventNotificationServiceImpl = ctx.getBean("eventNotificationServiceImpl", EventNotificationServiceImpl.class);
		
		eventNotificationServiceImpl.noticeEvent("할인 10%", "할인합니다.");
	}
}
