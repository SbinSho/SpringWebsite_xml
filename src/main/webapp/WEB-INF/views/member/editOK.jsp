<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
	<script type="text/javascript">
		var edit = "${edit}";
		var result = "${result}";

		if(result == "OK" && edit == "아이디"){
			alert( edit + " 변경완료!\n다시 로그인해주세요!");
			location.href="/member/logout";
		} else if(result == "OK"){
			alert( edit + " 변경완료!");
			location.href="/";
		} else {
			alert("잘못된 접근입니다.");
			location.href="/";
		}

	</script>


</body>
</html>