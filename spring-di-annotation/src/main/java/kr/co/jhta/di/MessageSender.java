package kr.co.jhta.di;

public interface MessageSender {

	void send(String from, String to, String subject, String content);
}
