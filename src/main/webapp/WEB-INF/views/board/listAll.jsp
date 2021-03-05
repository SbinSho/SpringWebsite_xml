<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">

</head>
<body>
	<table class="table table-bordered">
	  <thead>
	    <tr>
	      <th scope="col">이름</th>
	      <th scope="col">아이디</th>
	      <th scope="col">비밀번호</th>
	      <th scope="col">가입날짜</th>
	    </tr>
	  </thead>
	  <tbody>
	  <c:forEach items="${ list }" var="memberVO">
	    <tr>
	      <th scope="row">${ memberVO.username }</th>
	      <td>${ memberVO.userid }</td>
	      <td>${ memberVO.userpwd }</td>
	      <td>${ memberVO.rdate }</td>
	    </tr>
	  </c:forEach>
	  </tbody>
	</table>


	<div class="btn-toolbar" role="toolbar"
		aria-label="Toolbar with button groups">
		<div class="btn-group m-auto" role="group" aria-label="First group">
		<c:if test = "${ pageMaker.prev }">
			<button type="button" class="btn btn-secondary" onclick="location.href='<c:url value="/board/listAll?page=${ pageMaker.startPage-1 }"/>'">이전</button>
		</c:if>
		<c:forEach begin="${pageMaker.startPage }" end="${pageMaker.endPage }" var="pageNum">
			<button type="button" class="btn btn-secondary" onclick="location.href='<c:url value="/board/listAll?page=${ pageNum }"/>'">${ pageNum }</button>
		</c:forEach>
		<c:if test="${ pageMaker.next && pageMaker.endPage > 0 }">
			<button type="button" class="btn btn-secondary" onclick="location.href='<c:url value="/board/listAll?page=${ pageMaker.endPage + 1}"/>'">다음</button>
		</c:if>
		</div>
	</div>
	<!-- Option 1: jQuery and Bootstrap Bundle (includes Popper) -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>

</body>
</html>