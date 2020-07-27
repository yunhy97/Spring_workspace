package kr.co.jhta.step1;

import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.dao.DataAccessException;

import kr.co.jhta.dao.UserDao;
import kr.co.jhta.vo.User;

public class Main {

	public static void main(String[] args) {
		String conf = "classpath:/spring/context.xml";
		
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext(conf);
		
		UserDao userDao = ctx.getBean(UserDao.class);
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("[사용자관리]");
		try {
			while(true) {
				System.out.println("-------------------------------------------------------------------");
				System.out.println("1.전체조회 2.조회(아이디) 3.조회(이름) 4.등록 5.삭제(아이디) 6.전체삭제 0.종료");
				System.out.println("-------------------------------------------------------------------");
				
				System.out.println("메뉴 선택 > ");
				int menuNo = sc.nextInt();
				if(menuNo == 1) {
					System.out.println("[전체조회]");
					
				} else if(menuNo == 2) {
					System.out.println("[아이디로 조회]");
					
				} else if(menuNo == 3) {
					System.out.println("[이름으로 조회]");
					
				} else if(menuNo == 4) {
					System.out.println("[신규 사용자 등록]");
					
					System.out.print("아이디입력> ");
					String id = sc.next();
					System.out.print("비밀번호입력> ");
					String pwd = sc.next();
					System.out.print("이름입력> ");
					String name = sc.next();
					System.out.print("이메일입력> ");
					String email = sc.next();
					
					User user = new User();
					user.setId(id);
					user.setPassword(pwd);
					user.setName(name);
					user.setEmail(email);
					
					userDao.insertUser(user);
					System.out.println("<<사용자 등록 완료>>");
				} else if(menuNo == 5) {
					System.out.println("[아이디로 삭제]");
					
				} else if(menuNo == 6) {
					System.out.println("[전체 삭제]");
					
				} else if(menuNo == 0) {
					ctx.destroy();
					System.out.println("<<프로그램 종료>>");
				}
			}
		}catch (DataAccessException e) {
			e.printStackTrace();
		}
	}
}
