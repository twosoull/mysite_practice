<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="/jqueryex/jquery/jquery-1.12.4.js"></script>
<link href="${pageContext.request.contextPath}/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/guestbook.css" rel="stylesheet" type="text/css">

</head>

<body>
	<div id="wrap">

		<div id="header">
			<h1>
				<a href="">MySite</a>
			</h1>

			<ul>
				<li><a href="">로그인</a></li>
				<li><a href="">회원가입</a></li>
			</ul>
		</div>
		<!-- //header -->

		<div id="nav">
			<ul>
				<li><a href="">방명록</a></li>
				<li><a href="">갤러리</a></li>
				<li><a href="">게시판</a></li>
				<li><a href="">입사지원서</a></li>
			</ul>
			<div class="clear"></div>
		</div>
		<!-- //nav -->

		<div id="aside">
			<h2>방명록</h2>
			<ul>
				<li>일반방명록</li>
				<li>ajax방명록</li>
			</ul>
		</div>
		<!-- //aside -->

		<div id="content">

			<div id="content-head">
				<h3>일반방명록</h3>
				<div id="location">
					<ul>
						<li>홈</li>
						<li>방명록</li>
						<li class="last">일반방명록</li>
					</ul>
				</div>
				<div class="clear"></div>
			</div>
			<!-- //content-head -->

			<div id="guestbook">
				<!-- 	<form action="" method=""> -->
				<table id="guestAdd">
					<colgroup>
						<col style="width: 70px;">
						<col>
						<col style="width: 70px;">
						<col>
					</colgroup>
					<tbody>
						<tr>
							<th><label class="form-text" for="input-uname">이름</label></th>
							<td><input id="input-uname" type="text" name="name"></td>
							<th><label class="form-text" for="input-pass">패스워드</label></th>
							<td><input id="input-pass" type="password" name="pass"></td>
						</tr>
						<tr>
							<td colspan="4"><textarea id="input-content" name="content" cols="72" rows="5"></textarea></td>
						</tr>
						<tr class="button-area">
							<td colspan="4"><button id="btn_submit" type="submit">등록</button></td>
						</tr>
					</tbody>

				</table>
				<!-- //guestWrite -->
				<input type="hidden" name="action" value="add">

				<!-- 	</form>	 -->

				<div id="guestTableArea">

					<!-- 이곳에 guestTalbe이 작성된다 -->

				</div>
				<!-- //guestRead -->

			</div>
			<!-- //guestbook -->
		</div>
		<!-- //content  -->
		<div class="clear"></div>

		<div id="footer">Copyright ⓒ 2020 황일영. All right reserved</div>
		<!-- //footer -->

	</div>
	<!-- //wrap -->

</body>
<script type="text/javascript">
$("document").ready(function(){
	console.log("ready");
	
	$.ajax({
		
		url : "${pageContext.request.contextPath}/api/guest/list",  //컨트롤러의 url과 파라미터
		type : "post",	// 겟 포스트
		//contentType : "application/json",
		//data : {},

		dataType : "json",
		success : function(guestVoList){  //성공시
			for(var i = 0; i < guestVoList.length; i++){
				render(guestVoList[i],"down");
			}
		},
		error : function(XHR, status, error) { //실패
			console.error(status + " : " + error);
		}
	});


});



	$("#btn_submit").on("click", function() {
		var name = $("#input-uname").val();
		var password = $("#input-pass").val();
		var content = $("#input-content").val();
		console.log(name);
		console.log(password);
		console.log(content);

		$.ajax({

			url : "${pageContext.request.contextPath }/api/guest/write", //컨트롤러의 url과 파라미터
			type : "post", // 겟 포스트
			//contentType : "application/json",
			data : {
				name : name,
				password : password,
				content : content
			},

			dataType : "json",
			success : function(guestVo) { //성공시
				console.log(guestVo);
				render(guestVo,"up");
			},
			error : function(XHR, status, error) { //실패
				console.error(status + " : " + error);
			}
		});
	});
	
function render(guestVo,updown){
	var str = "";
	str += "<table class='guestRead'>";
	str += "<colgroup>";
	str += "	<col style='width: 10%;'>";
	str += "	<col style='width: 40%;'>";
	str += "	<col style='width: 40%;'>";
	str += "	<col style='width: 10%;'>";
	str += "</colgroup>";
	str += "<tr>";
	str += "	<td>"+guestVo.no+"</td>";
	str += "	<td>"+guestVo.name+"</td>";
	str += "	<td>"+guestVo.regDate+"</td>";
	str += "	<td><a href=''>[삭제]</a></td>";
	str += "</tr>";
	str += "<tr>";
	str += "	<td colspan=4 class='text-left'>"+guestVo.content+"</td>";
	str += "</tr>";
	str += "</table>";
	
	if(updown == "down"){
		$("#guestTableArea").append(str);
	}else if (updown =="up"){
		$("#guestTableArea").prepend(str);
	}
	
}
</script>


</html>