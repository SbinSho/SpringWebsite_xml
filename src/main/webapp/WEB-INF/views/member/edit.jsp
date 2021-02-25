<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:400,700">
	<title>Hello</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
	<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
	<style>
	body {
		color: #fff;
		background: #63738a;
		font-family: 'Roboto', sans-serif;
	}
	.form-control {
		height: 40px;
		box-shadow: none;
		color: #969fa4;
	}
	.form-control:focus {
		border-color: #5cb85c;
	}
	.form-control, .btn {        
		border-radius: 3px;
	}
	.signup-form {
		width: 450px;
		margin: 0 auto;
		padding: 30px 0;
	  	font-size: 15px;
	}
	.signup-form h2 {
		color: #636363;
		margin: 0 0 15px;
		position: relative;
		text-align: center;
	}
	.signup-form h2:before, .signup-form h2:after {
		content: "";
		height: 2px;
		width: 30%;
		background: #d4d4d4;
		position: absolute;
		top: 50%;
		z-index: 2;
	}	
	.signup-form h2:before {
		left: 0;
	}
	.signup-form h2:after {
		right: 0;
	}
	.signup-form .hint-text {
		color: #999;
		margin-bottom: 30px;
		text-align: center;
	}
	.signup-form form {
		color: #999;
		border-radius: 3px;
		margin-bottom: 15px;
		background: #f2f3f7;
		box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
		padding: 30px;
	}
	.signup-form .form-group {
		margin-bottom: 20px;
	}
	.signup-form input[type="checkbox"] {
		margin-top: 3px;
	}
	.signup-form .btn {        
		font-size: 16px;
		font-weight: bold;		
		min-width: 140px;
		outline: none !important;
	}
	.signup-form .row div:first-child {
		padding-right: 10px;
	}
	.signup-form .row div:last-child {
		padding-left: 10px;
	}    	
	.signup-form a {
		color: #fff;
		text-decoration: underline;
	}
	.signup-form a:hover {
		text-decoration: none;
	}
	.signup-form form a {
		color: #5cb85c;
		text-decoration: none;
	}	
	.signup-form form a:hover {
		text-decoration: underline;
	}  
	</style>
</head>
<body>
<div class="signup-form">
		<h2>회원정보</h2>
        <div class="form-group">
        	<label for="user_name">이름</label>
			<input id="user_name" type="text" class="form-control" value="${ memberVO.username }" readonly/>
        </div>

        <div class="form-group">
        	<label for="user_id">아이디</label>
			<input id="user_id" class="form-control" value="${ memberVO.userid }" readonly/>
        </div>
		<div class="form-group">
            <button type="button" onclick="location.href='<c:url value='/member/edit/chid/${ memberVO.userid }'/>'" class="btn btn-primary btn-lg btn-block">아이디 변경</button>
        </div>
		<div class="form-group">
            <button type="button" onclick="location.href='<c:url value='/member/edit/chpass'/>'" class="btn btn-primary btn-lg btn-block">비밀번호 변경</button>
        </div>
		<div class="form-group">
            <button type="button" onclick="location.href='<c:url value='/'/>'" class="btn btn-primary btn-lg btn-block">수정취소</button>
        </div>
</div>

<script>
	// 프론트에서 유효성 검사 -> spring valid 유효성 검사 -> DB 회원가입 정보 추가
	
	// form 값 전송시에 좀 더 효율적인 방법을 사용하기 위해 선언 ( 임시 )
	var nameFlag = false;
	var idFlag = false;
	var pwFlag = false;
	var pw_chFlag = false;
	
	
	// 아이디 유효성 검사
	$("#user_id").click(function(){
		// naver ID 정규식 검사
		var isId = /^[a-z0-9][a-z0-9_\-]{4,19}$/;
		var userid = $("#user_id").val();

		if(!(isId.test(userid))){
			$("#userid_check").css("color", "red");
			$("#userid_check").html("5~20자의 영문 소문자, 숫자와 특수기호(_),(-)만 사용 가능합니다.");

			idFlag = false;
			 
			return false;
		}
		
		$.ajax({
			type : "POST",
			data : { id : userid },
			url : "/member/idCheck",
			dataType: 'json',
			success : function(data){
				if(data == 1){
					$("#userid_check").css("color", "red");
					$("#userid_check").html("이미 사용중이거나 탈퇴한 아이디입니다.");
					
					idFlag = false;
				}
				else {
					$("#userid_check").css("color", "green");
					$("#userid_check").html("멋진 아이디네요!");
					
					idFlag = true;
				}
			},
			error : function(error){
				console.log(error);
			},
			
		});

	});

	// 비밀번호 유효성 검사
	$("#user_pwd").blur(function(){

		var isPW = /^.*(?=.{6,20})(?=.*[0-9])(?=.*[a-zA-Z]).*$/;
		var password = $("#user_pwd").val();

		
		if( isPW.test(password) ) {
			$("#password_check").css("color", "green");
			$("#password_check").html("비밀번호 사용가능!");
			pwFlag = true;
			
		} else {
			$("#password_check").css("color", "red");
			$("#password_check").html("영문,숫자를 혼합하여 6~20자 이내로 입력해주세요.");
			pwFlag = false;
		}
		
		
	});

	// 비밀번호 확인란 체크
	$("#user_con_pwd").blur(function(){
		
		var password = $("#user_pwd").val();
		var confirm_password = $("#user_con_pwd").val();

		if( password == confirm_password){
			$("#password_con_check").css("color", "green");
			$("#password_con_check").html("패스워드 확인 완료!");
			pw_chFlag = true;
			
		} else {
			$("#password_con_check").css("color", "red");
			$("#password_con_check").html("비밀번호를 확인해주세요.");
			pw_chFlag = false;
			
		}

	});

	
	// 회원가입 폼 전송 전 최종 유효성 검사
	function check() {
		var user_name = $("#user_name").val();
		var user_id = $("#user_id").val();
		var user_password = $("#user_pwd").val();
		
		if (user_name == "" || user_id == "" || user_password == "") {

			alert("입력되지 않은 정보가 존재합니다.");
			return false;
		}
		
		// 모든 입력란의 유효성 검증 체크
		if (nameFlag == true && idFlag == true && pwFlag == true && pw_chFlag == true) {

			var confirm_check = confirm("현재 입력된 정보로 가입하시겠습니까?");
			
			if(confirm_check == true){
				return true;
				
			} 

			return false;

		} else {

			alert("잘못된 입력 정보가 있습니다!");
			return false;
			
		}

	}


</script>

</body>
</html>