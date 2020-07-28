package kr.co.jhta;

import java.util.List;

import kr.co.jhta.dao.BlogDao;
import kr.co.jhta.dao.CommentDao;
import kr.co.jhta.dao.UserDao;
import kr.co.jhta.dto.BlogDto;
import kr.co.jhta.vo.Blog;

public class Main {
	
	public static void main(String[] args) {
		
		UserDao userDao = new UserDao();
		BlogDao blogDao = new BlogDao();
		CommentDao commentDao = new CommentDao();
		
//		System.out.println("게시물을 등록합니다.");
//		Blog blog = new Blog("안녕","hong","안녕하세요");
//		blogDao.insertBlog(blog);
//		System.out.println("게시물 등록이 완료되었습니다.");
		
		System.out.println("모든 게시물 보기");
		List<BlogDto> blogDtos = blogDao.getAllBlogs();
		for(BlogDto dto : blogDtos) {
			
			System.out.println(dto.getNo()+", "+dto.getTitle()+", "+ dto.getUserName());
		}
		System.out.println();
		
		System.out.println("게시물 번호로 조회하기");
		BlogDto blogDto = blogDao.getBlogByNo(3);
		System.out.println(blogDto);
		if(blogDto != null) {
			System.out.println(blogDto.getNo() + "," + blogDto.getTitle()+ "," +blogDto.getContent()+","+ blogDto.getUserName());
		} else {
			System.out.println("지정된 아이디의 게시물이 존재하지 않습니다.");
		}
		System.out.println();
		
//		System.out.println("게시물 수정하기");
//		Blog blog = new Blog(2,"하이","hong","hi");
//		blogDao.updateBlog(blog);
//		System.out.println("수정 완료되었습니다.");
		
		
	}
}
