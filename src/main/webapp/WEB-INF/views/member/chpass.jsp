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
    <form:form commandName="memberPassDTO" method="post" onsubmit="return check()">
        <form:hidden path="userid"/>
        <div class="form-group">
        	<label for="userpwd">현재 비밀번호</label>
        	<form:password path="userpwd" id="userpwd" class="form-control" />
        	<form:errors path="userpwd" />
        </div>

        <div class="form-group">
        	<label for="ch_userpwd">새 비밀번호</label>
        	<form:password path="ch_userpwd" id="ch_userpwd" class="form-control" />
        	<form:errors path="ch_userpwd" />
        	<span id="password_check"></span>
        </div>
        <div class="form-group">
        	<label for="ch_userpwd_confirm">새 비밀번호 확인</label>
        	<input type="password" id="ch_userpwd_confirm" class="form-control">
        	<span id="password_con_check"></span>
        </div>
		<div class="form-group">
            <button type="submit" class="btn btn-primary btn-lg btn-block">수정하기</button>
        </div>
		<div class="form-group">
            <button type="button" onclick="history.back();" class="btn btn-danger btn-lg btn-block">뒤로가기</button>
        </div>
    </form:form>
</div>

<script type="text/javascript">

	var result = "${result}";
	if(result == "error"){
		alert("비밀번호를 확인해주세요!");
	}

	var isPW = /^.*(?=.{6,20})(?=.*[0-9])(?=.*[a-zA-Z]).*$/;

	var pwFlag;
    var ch_pwFlag;
	
	// 비밀번호 유효성 검사
	$("#ch_userpwd").blur(function(){
		var ch_userpwd = $("#ch_userpwd").val();
		
		if(isPW.test(ch_userpwd)){
			$("#password_check").css("color", "green");
			$("#password_check").html("비밀번호 사용가능!");
			pwFlag = true;
		}
		else {
			$("#password_check").css("color", "red");
			$("#password_check").html("영문,숫자를 혼합하여 6~20자 이내로 입력해주세요.");
			pwFlag = false;		
		}

	});

	// 비밀번호 확인란 체크
	$("#ch_userpwd_confirm").blur(function(){
		
		var ch_userpwd = $("#ch_userpwd").val();
		var ch_userpwd_confirm = $("#ch_userpwd_confirm").val();

		if( ch_userpwd == ch_userpwd_confirm){
			$("#password_con_check").css("color", "green");
			$("#password_con_check").html("패스워드 확인 완료!");
			pw_chFlag = true;
			
		} else {
			$("#password_con_check").css("color", "red");
			$("#password_con_check").html("비밀번호를 확인해주세요.");
			pw_chFlag = false;
			
		}


	});
	
	function check() {

		var userpwd = $("#userpwd").val();
		var ch_userpwd = $("ch_userpwd").val();
		var ch_userpwd_confirm = $("ch_userpwd_confirm").val();

		if( userpwd == "" || ch_userpwd == "" || ch_userpwd_confirm == ""){

			alert("입력되지 않은 정보가 있습니다.");
			return false;
		}

		if ( pw_Flag == true && ch_pwFlag == true){
			return true;
		}
		else {
			alert("잘못된 입력 정보가 있습니다!");
			return false;
		}
	}

	
</script>

</body>
</html>