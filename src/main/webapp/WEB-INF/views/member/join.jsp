<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:400,700">
	<title>Bootstrap Simple Registration Form</title>
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
    <form:form commandName="memberVO" method="post">
		<h2>회원가입</h2>
		<p class="hint-text">Create your account. It's free and only takes a minute.</p>
        <div class="form-group">
			<form:input path="username" type="text" class="form-control" placeholder="이름"/>
			<form:errors path="username" />
        </div>

        <div class="form-group">
			<form:input  path="userid" id="userid" type="text" class="form-control" placeholder="아이디" />
			<form:errors path="userid"/>
			<span id="userid_check"></span>
        </div>
		<div class="form-group">
            <form:input  path="userpwd" type="password" class="form-control" id="passowrd" name="userpwd" placeholder="비밀번호" />
            <form:errors path="userpwd" />
        </div>
		<div class="form-group">
            <input type="password" class="form-control" id="confirm_password" placeholder="비밀번호 확인">
           	<span id="password_check"></span>
        </div>
		<div class="form-group">
            <button type="submit" class="btn btn-primary btn-lg btn-block">회원가입</button>
        </div>
    </form:form>
    
	<div class="text-center">
		Already have an account? <a href="<c:url value='/member/login'/>">로그인</a>
	</div>
</div>

<script>
	// 프론트에서 유효성 검사 -> spring valid 유효성 검사 -> DB 회원가입 정보 추가
	
	
	// 아이디 유효성 검사
	$("#userid").blur(function(){
		// naver ID 정규식 검사
		var isID = /^[a-z0-9][a-z0-9_\-]{4,19}$/;
		var userid = $("#userid").val();

		if(userid == ""){
			$("#userid_check").html("아이디를 입력해주세요!");
			return false;
		}

		if(!(isID.test(userid))){
			$("#userid_check").html("5~20자의 영문 소문자, 숫자와 특수기호(_),(-)만 사용 가능합니다.");
			return false;
		}
		
		$.ajax({
			type : "POST",
			data : { id : userid },
			url : "/member/idCheck",
			dataType: 'json',
			success : function(data){
				if(data == 1){
					$("#userid_check").html("이미 사용중이거나 탈퇴한 아이디입니다.");
				}
				else {
					$("#userid_check").html("멋진 아이디네요!");
				}
			},
			error : function(error){
				console.log(error);
			},
			
		});

	});

	// 패스워드 유효성 검사
	$("#password").blur(function(){
		var password = $("#password").val();

		

	});

	// 패스워드 확인란 체크
	$("#confirm_password").blur(function(){
		
		var password = $("#passowrd").val();
		var confirm_password = $("#confirm_password").val();

		if( password == confirm_password){
			$("#password_check").html("패스워드 확인 완료!");
		} else {
			$("#password_check").html("비밀번호를 확인해주세요.");
		}

	});
</script>

</body>
</html>