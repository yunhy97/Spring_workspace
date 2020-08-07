<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
				<form id="form-new-board" method="POST" action="add.do" enctype="multipart/form-data">
					<div class="card">
						<div class="card-header">새 글 입력폼</div>
						<div class="card-body">
							<div class="form-group">
								<label>제목</label>
								<input type="text" class="form-control" name="title" id="board-title" />
							</div>
							<div class="form-group">
								<label>비밀번호</label>
								<input type="password" class="form-control" name="password" id="board-password" />
							</div>
							<div class="form-group">
								<label>내용</label>
								<textarea rows="5" class="form-control" name="content"></textarea>
							</div>
							<div class="form-group">
								<label>첨부 파일</label>
								<input type="file" class="form-control" name="upfile" />
							</div>
						</div>
						<div class="card-footer text-right">
							<a href="list.do" class="btn btn-secondary">취소</a>
							<button type="submit" class="btn btn-primary">등록</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
<script type="text/javascript" src="/resources/jquery/jquery.min.js"></script>
<script>
// 페이지 로딩 시 자동 실행되는 함수
$(function() {
	// 입력 폼에서 submit 이벤트 발생 시 실행할 이벤트 핸들러 함수 실행해두기
	$("#form-new-board").submit(function() {
		var $titleField = $("#board-title");
		var $writerField = $("#board-writer");
		var $passwordField = $("#board-password");
		
		if($titleField.val() == "") {
			alert("작성자는 필수 입력 사항입니다.");
			$writerField.focus();
			return false;
		}
		
		if($writerField.val() == "") {
			alert("작성자는 필수 입력 사항입니다.");
			$writerField.focus();
			return false;
		}
		
		if($passwordField.val() == "") {
			alert("비밀번호는 필수 입력 사항입니다.");
			$passwordField.focus();
			return false;
		}
		
		return true;
	});
});
</script>
</body>
</html>