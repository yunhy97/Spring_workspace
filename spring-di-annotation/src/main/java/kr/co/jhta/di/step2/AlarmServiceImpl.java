package kr.co.jhta.di.step2;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.jhta.di.MessageSender;

@Service
public class AlarmServiceImpl implements AlarmService {
	//@Autowired == @Resource
	//@Autowired != @Resource(name="kakao")
	@Resource//(name="kakao")
	MessageSender kakao;
	
	@Override
	public void alarm(String message) {
		kakao.send("민방위본부", "전국민", "비상사태", message);
	}
}
