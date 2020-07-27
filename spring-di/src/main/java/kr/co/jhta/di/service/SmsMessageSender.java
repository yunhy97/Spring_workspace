package kr.co.jhta.di.service;

public class SmsMessageSender implements MessageSender{
	
	private String company;
	
	public void setCompany(String company) {
		this.company = company;
	}
	
  @Override
   public void send(String from, String to, String subject, String content) {
      System.out.println(company + "통신사를 이용합니다.");
      System.out.println("SMS 발신자 : "+ from);
      System.out.println("SMS 수신자 : "+ to);
      System.out.println("SMS 제목 : "+ subject);
      System.out.println("SMS 내용 : "+ content);
      System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
   }
	
}
