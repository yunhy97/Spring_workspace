package kr.co.jhta.dao;

import kr.co.jhta.vo.Blog;

public interface BlogDao {

	void insertBlog(Blog blog);
	
	Blog getBlogByNo(int blogNo);
}
