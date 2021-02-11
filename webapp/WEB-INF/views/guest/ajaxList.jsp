<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/guestbook.css" rel="stylesheet" type="text/css">

<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.12.4.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/bootstrap/js/bootstrap.js"></script>
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
	<div class="modal fade" id="delModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">방명록삭제</h4>
				</div>
				<div class="modal-body">
					<label>비밀번호</label><input id="modalPassword" type="text" name="password"> <input id="modalNo" type="text" name="no">
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">취소</button>
					<button id="btnModalDel" type="button" class="btn btn-primary">삭제</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
</body>
<script type="text/javascript">
	//dom
	$("document").ready(function() {
		console.log("ready");

		fetchList();

	}); //dom 끝

	//등록버튼
	$("#btn_submit").on("click", function() {

		var guestVo = {
			name : $("#input-uname").val(),
			password : $("#input-pass").val(),
			content : $("#input-content").val()
		};
		
		
		$.ajax({

			url : "${pageContext.request.contextPath }/api/guest/write", //컨트롤러의 url과 파라미터
			type : "post", // 겟 포스트
			//contentType : "application/json",
			data : guestVo,

			dataType : "json",
			success : function(guestVo) { //성공시
				console.log(guestVo);
				render(guestVo, "up");
				$("[name='name']").val("");
				$("[name='pass']").val("");
				$("[name='content']").val("");
			},
			error : function(XHR, status, error) { //실패
				console.error(status + " : " + error);
			}
		});
	});// 등록버튼 끝

	//삭제버튼
	$("#guestTableArea").on("click", "a", function() {
		event.preventDefault();

		console.log("삭제버튼");
		//모달창은 이미 html 하단에 준비 되어있으니 값만 넣어주면 알아서 세팅이된다
		//data-no='"+guestVo.no+" 로 표시한 a의태그의 값을 불러오고
		var no = $(this).data("no");
		$("#modalNo").val(no);
		$("#modalPassword").val("");
		//모달창 띄우기
		$("#delModal").modal();

	});

	//모달창 삭제버튼
	$("#btnModalDel").on("click", function() {
		console.log("모달삭제버튼");
		
		var guestVo = {
			password : $("#modalPassword").val(),
			no : $("#modalNo").val()
		};

		$.ajax({

			url : "${pageContext.request.contextPath }/api/guest/remove", //컨트롤러의 url과 파라미터
			type : "post", // 겟 포스트
			contentType : "application/json",
			data : JSON.stringify(guestVo),

			dataType : "json",
			success : function(count) { //성공시
				console.log("count" + count);
				if (count == 0) {
					alert("비밀번호가 틀렸습니다")

					$("#modalPassword").val("");
				} else if (count == 1) {

					$("#delModal").modal("hide");

					$("#modalPassword").val("");
					$("#t-" + guestVo.no).remove();
				}

			},
			error : function(XHR, status, error) { //실패
				console.error(status + " : " + error);
			}
		});
	});

	function render(guestVo, updown) {
		var str = "";
		str += "<table id='t-"+guestVo.no+"'class='guestRead'>";
		str += "<colgroup>";
		str += "	<col style='width: 10%;'>";
		str += "	<col style='width: 40%;'>";
		str += "	<col style='width: 40%;'>";
		str += "	<col style='width: 10%;'>";
		str += "</colgroup>";
		str += "<tr>";
		str += "	<td>" + guestVo.no + "</td>";
		str += "	<td>" + guestVo.name + "</td>";
		str += "	<td>" + guestVo.regDate + "</td>";
		str += "	<td><a id='btnDel' href='' data-no='"+guestVo.no+"'>[삭제]</a></td>";
		str += "</tr>";
		str += "<tr>";
		str += "	<td colspan=4 class='text-left'>" + guestVo.content + "</td>";
		str += "</tr>";
		str += "</table>";

		if (updown == "down") {
			$("#guestTableArea").append(str);
		} else if (updown == "up") {
			$("#guestTableArea").prepend(str);
		}

	}

	function fetchList() {
		console.log("fetchList");
		$.ajax({

			url : "${pageContext.request.contextPath}/api/guest/list", //컨트롤러의 url과 파라미터
			type : "post", // 겟 포스트
			//contentType : "application/json",
			//data : {},

			dataType : "json",
			success : function(guestVoList) { //성공시
				for (var i = 0; i < guestVoList.length; i++) {
					render(guestVoList[i], "down");
				}

			},
			error : function(XHR, status, error) { //실패
				console.error(status + " : " + error);
			}
		});

	};
</script>


</html>