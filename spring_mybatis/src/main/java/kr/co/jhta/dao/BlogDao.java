package kr.co.jhta.dao;

import java.util.List;

import kr.co.jhta.vo.Blog;

public interface BlogDao {

	List<Blog> getBlogs();
	
	void insertBlog(Blog blog);
	
	Blog getBlogByNo(int blogNo);
	
	List<Blog> getBlogsByWriter(String writer);
	
	void updateBlog(Blog blog);
	
	void deleteBlogByNo(int blogNo);
	
	void deleteBlogsByWriter(String writer);
}
