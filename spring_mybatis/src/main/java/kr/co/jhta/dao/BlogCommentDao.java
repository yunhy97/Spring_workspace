package kr.co.jhta.dao;

import java.util.List;

import kr.co.jhta.vo.Comment;

public interface BlogCommentDao {

	void insertComment(Comment comment);
	List<Comment> getCommentsByBlogNo(int blogNo);
	List<Comment> getCommentsByWriter(String writer);
	void deleteCommentByNo(int commentNo);
	void deleteCommentsByBlogNo(int blogNo);
	void deleteCommentsByWriter(String writer);
	
}
