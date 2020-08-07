<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="/resources/bootstrap/css/bootstrap.min.css">
<title>쇼핑몰</title>
</head>
<body>
<div class="container">
	<div class="row">
		<div class="col-12">
			<form id="product-form" method="post" action="add.do">
				<div class="card">
					<div class="card-header">새 상품 등록폼</div>
					<div class="card-body">
						<div class="form-group">
							<label>카테고리</label>
							<select class="form-control" name="catId" id="category-id">
								<option value="" selected disabled>-- 선택하세요 --</option>
								<c:forEach var="category" items="${categories }">
									<option value="${category.id }">${category.name }</option>
								</c:forEach>
							</select>
						</div>
						<div class="form-group">
							<label>상품 이름</label>
							<input type="text" class="form-control" name="name" id="product-name"/>
						</div>
						<div class="form-group">
							<label>상품 가격</label>
							<input type="number" class="form-control" name="price" id="product-price"/>
						</div>
						<div class="form-group">
							<label>상품 할인가격</label>
							<input type="number" class="form-control" name="discountPrice" id="product-discount_price"/>
						</div>
					</div>
					<div class="card-footer text-right">
						<a href="list.do" class="btn btn-secondary">취소</a>
						<button type="submit" class="btn btn-primary">제품 등록</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
</body>
</html>