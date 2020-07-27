package kr.co.jhta.di.service.step8;

import org.springframework.context.support.GenericXmlApplicationContext;

public class Main2 {

	public static void main(String[] args) {
		String conf = "kr/co/jhta/di/service/step8/context-step8-map.xml";
		
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext(conf);
		
		NoticeService noticeService = ctx.getBean("noticeServiceUsingMap",NoticeService.class);
		noticeService.notice("**팀", "안녕하세요", "반갑습니다.");
		
		ctx.destroy();
	}
	
	
}
