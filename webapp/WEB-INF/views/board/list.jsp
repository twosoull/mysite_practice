<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/board.css" rel="stylesheet" type="text/css">

</head>



<body>
	<div id="wrap">

		<c:import url="/WEB-INF/views/include/header.jsp"></c:import>
		<!-- //header -->


		<!-- //nav -->

		<div id="aside">
			<h2>게시판</h2>
			<ul>
				<li><a href="${pageContext.request.contextPath}/board/list">일반게시판</a></li>
				<li><a href="${pageContext.request.contextPath}/rboard/list">댓글게시판</a></li>
			</ul>
		</div>
		<!-- //aside -->

		<div id="content">

			<div id="content-head">
				<h3>게시판</h3>
				<div id="location">
					<ul>
						<li>홈</li>
						<li>게시판</li>
						<li class="last">일반게시판</li>
					</ul>
				</div>
				<div class="clear"></div>
			</div>
			<!-- //content-head -->

			<div id="board">
				<div id="list">
					<form action="" method="">
						<div class="form-group text-right">
							<input type="text">
							<button type="submit" id=btn_search>검색</button>
						</div>
					</form>
					<table>
						<thead>
							<tr>
								<th>번호</th>
								<th>제목</th>
								<th>글쓴이</th>
								<th>조회수</th>
								<th>작성일</th>
								<th>관리</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${boardList}" var="vo" varStatus="st">
								<tr>
									<td>${vo.no}</td>
									<td class="text-left"><a href="${pageContext.request.contextPath}/board/read?no=${vo.no}&pageNum=${boardPage.pageNow}">${vo.title }</a></td>
									<td>${vo.name }</td>
									<td>${vo.hit }</td>
									<td>${vo.regDate}</td>
									<td><c:if test="${vo.userNo == authUser.no }">
											<a href="${pageContext.request.contextPath}/board/remove?no=${vo.no}">[삭제]</a>
										</c:if></td>
								</tr>
							</c:forEach>

						</tbody>
					</table>

					<div id="paging">
						<ul>
							<li><a href="${pageContext.request.contextPath}/board/list?pageNum=${boardPage.prev}">◀</a></li>
							<c:forEach begin="1" end="${boardPage.page}" step="1" varStatus="st">
							

									<li><a href="${pageContext.request.contextPath}/board/list?pageNum=${st.count}"><strong>${st.count}</strong></a></li>

									<li><a href="${pageContext.request.contextPath}/board/list?pageNum=${st.count}">${st.count}</a></li>


							

							</c:forEach>
							<li><a href="${pageContext.request.contextPath}/board/list?pageNum=${boardPage.next}">▶</a></li>
						</ul>


						<div class="clear"></div>
					</div>
					<c:if test="${!empty authUser }">
						<a id="btn_write" href="${pageContext.request.contextPath}/board/writeForm">글쓰기</a>
					</c:if>

				</div>
				<!-- //list -->
			</div>
			<!-- //board -->
		</div>
		<!-- //content  -->
		<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
		<!-- //footer -->
	</div>
	<!-- //wrap -->

</body>

</html>
