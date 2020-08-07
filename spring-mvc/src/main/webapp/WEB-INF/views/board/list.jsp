<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="/resources/bootstrap/css/bootstrap.min.css">
	<title>자유 게시판</title>
</head>
<body>
	<div class="container mt-3 mb-5">
		<div class="row">
			<div class="col-12">
				<div class="card">
					<div class="card-header">게시글 목록</div>
					<div class="card-body">
						<table class="table">
							<colgroup>
								<col width="10%">
								<col width="*">
								<col width="15%">
								<col width="15%">
								<col width="15%">
							</colgroup>
							<thead>
								<tr>
									<th>번호</th>
									<th>제목</th>
									<th>작성자</th>
									<th>조회수</th>
									<th>등록일</th>
								</tr>
							</thead>
							<tbody>
								<c:choose>
									<c:when test="${empty boards }">
										<tr><td colspan="5">작성된 게시글이 없습니다.</td></tr>
									</c:when>
									<c:otherwise>
										<c:forEach var="board" items="${boards }">
											<tr>
												<td>${board.no }</td>
												<td><a href="likes.do?no=${board.no }"><c:out value="${board.title }" /></a></td>
												<td><c:out value="${board.writer }" /></td>
												<td>${board.likes }</td>
												<td><fmt:formatDate value="${board.createdDate }" /></td>
											</tr>
										</c:forEach>
									</c:otherwise>
								</c:choose>
							</tbody>
						</table>
					</div>
					<div class="card-footer text-right">
						<a href="add.do" class="btn btn-primary">새 글</a>
					</div>
				</div>
			</div>	
		</div>
	</div>
</body>
</html>