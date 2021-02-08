<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.12.4.js"></script>

<link href="${pageContext.request.contextPath}/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/user.css" rel="stylesheet" type="text/css">


</head>

<body>
	<div id="wrap">

		<c:import url="/WEB-INF/views/include/header.jsp"></c:import>
		<!-- //header -->

		<!-- //nav -->

		<div id="aside">
			<h2>회원</h2>
			<ul>
				<li>회원정보</li>
				<li>로그인</li>
				<li>회원가입</li>
			</ul>
		</div>
		<!-- //aside -->

		<div id="content">

			<div id="content-head">
				<h3>회원가입</h3>
				<div id="location">
					<ul>
						<li>홈</li>
						<li>회원</li>
						<li class="last">회원가입</li>
					</ul>
				</div>
				<div class="clear"></div>
			</div>
			<!-- //content-head -->

			<div id="user">
				<div id="joinForm">
					<form action="${pageContext.request.contextPath}/user/join" method="">


						<!-- 아이디 -->
						<div class="form-group">
							<label class="form-text" for="input-uid">아이디</label> 
							<input type="text" id="input-uid" name="id" value="" placeholder="아이디를 입력하세요">
							<button type="button" id="btnCheck">중복체크</button>
						</div>
						<p id="msg"></p>
						<!-- 비밀번호 -->
						<div class="form-group">
							<label class="form-text" for="input-pass">패스워드</label>
							<input type="text" id="input-pass" name="password" value="" placeholder="비밀번호를 입력하세요">
						</div>

						<!-- 이메일 -->
						<div class="form-group">
							<label class="form-text" for="input-name">이름</label> <input type="text" id="input-name" name="name" value="" placeholder="이름을 입력하세요">
						</div>
						<p id="noName"></p>
						<!-- //나이 -->
						<div class="form-group">
							<span class="form-text">성별</span>
							<label for="rdo-male">남</label> 
							<input type="radio" id="rdo-male" name="gender" value="male"> 
							<label for="rdo-female">여</label> 
							<input type="radio" id="rdo-female" name="gender" value="female">

						</div>

						<!-- 약관동의 -->
						<div class="form-group">
							<span class="form-text">약관동의</span> 
							<input type="checkbox" id="chk-agree" value="" name="">
							 <label for="chk-agree">서비스 약관에 동의합니다.</label>
						</div>


						<div class="button-area">
							<button type="submit" id="btn-submit">회원가입</button>
						</div>

					</form>
				</div>
				<!-- //joinForm -->
			</div>
			<!-- //user -->
		</div>
		<!-- //content  -->
		<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
		<!-- //footer -->

	</div>
	<!-- //wrap -->

</body>
<script type="text/javascript">
	//아이디중복
	$("#btnCheck").on("click",function(){
		//아이디값 받기
		var uid = $("#input-uid").val();
		//ajax를 이용해서 아이디 --> 컨트롤러 ~ sql~xml
		//데이터베이스를 통해 동일한 아이디 확인후 사용가능 실패값 보내기
		
		$.ajax({
			//값을보낼 url
			url : "${pageContext.request.contextPath}/user/checkId",  //컨트롤러의 url과 파라미터
			type : "post",	// 겟 포스트
			//contentType : "application/json",
			data : {id: uid}, //보낼 파라미터값

			dataType : "text", //값을 받는 타입
			success : function(result){  //성공시
				console.log(result);
			
				if(result == 'cant'){
					$("#msg").html("중복된 아이디가 있습니다")
				}else if(result == 'can'){
					$("#msg").html("사용할 수 있는 아이디입니다")
				};
			
			},
			error : function(XHR, status, error) { //실패
				console.error(status + " : " + error);
			}
		});

	});
	//패스워드 8자이상 , 약관동의 경고문
	$("#joinForm").on("submit",function(){
		//패스워드값 받기
		var pass = $("#input-pass").val();
		var check = $("#chk-agree").is(":checked")
		var uid = $("#input-uid").val();
		var name = $("#input-name").val();
		var gender = $("[name='gender']:checked").val();
		console.log(gender);
		
		if(uid == ""){
			alert("id를 입력해주세요");
			return false;
		}
		if(pass.length < 8){
			alert("비밀번호는 8자 이상 입력해주세요");
			return false;
		};
		if(name == ""){
			$("#noName").text("이름을 기입해주세요");
			return false;
		}else{
			$("#noName").text("");
		}
		
		if(gender == null){
			alert("성별을 체크해주세요");
			return false;
		}
		if(!check == true){
			alert("약관에 동의해주세요");
			
			return false;
		}
		return true;
	});
	

</script>

</html>