<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.servletContext.contextPath}/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<script src="${pageContext.servletContext.contextPath}/assets/js/jquery/jquery-1.9.0.js"></script>
<script>
$(function() {
	$("#kwd").focus();
});
</script>
<body>
	<div id="container">
		<c:import url='/WEB-INF/views/includes/header.jsp' />
		<div id="content">
			<div id="board">
				<form id="search_form" action="${pageContext.servletContext.contextPath }/board" method="get">
					<input type="text" id="kwd" name="kwd" value="">
					<input type="submit" value="찾기">
				</form>
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>
					<c:set var="count" value="${fn:length(list)}" />
					<c:forEach items="${list}" var="vo" varStatus="status">
					<tr>
						<td>${vo.no}</td>
						<td style="text-align:left; padding-left:${10*vo.orderNo}px">
							<a href="${pageContext.servletContext.contextPath}/board/get/${vo.no}">${vo.title }</a></td>
						<td>${vo.userName}</td>
						<td>${vo.hit }</td>
						<td>${vo.regDate }</td>
						<td style='width:20px; height:20px;'>
							<c:if test='${vo.userNo eq authUser.no}'>
							<a href="${pageContext.servletContext.contextPath}/board/delete/${vo.no}/${vo.userNo}" class="del">삭제</a>
							</c:if>
						</td>
					</tr>
					</c:forEach>
				</table>
				
				<!-- pager 추가 -->
				<div class="pager">
					<ul>
						<li><a href="${pageContext.servletContext.contextPath}/board?p=${pageNo-1}">◀</a></li>
						<c:forEach begin='1' end='${pageRange}' varStatus='status'>
							<c:choose>
								<c:when test='${pageNo eq pageRangeNo*pageRange+status.index }'>
								<li class="selected"><a href="${pageContext.servletContext.contextPath}/board?p=${pageRangeNo*pageRange+status.index}">${pageRangeNo*pageRange+status.index}</a></li>
								</c:when>
								<c:otherwise>
								<li><a href="${pageContext.servletContext.contextPath}/board?p=${pageRangeNo*pageRange+status.index}">${pageRangeNo*pageRange+status.index}</a></li>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						<li><a href="${pageContext.servletContext.contextPath}/board?p=${pageNo+1}">▶</a></li>
					</ul>
				</div>					
				<!-- pager 추가 -->
				
				<!-- pager 추가
				<div class="pager">
					<ul>
						<li><a href="">◀</a></li>
						<li><a href="">1</a></li>
						<li class="selected">2</li>
						<li><a href="/board/list?p=3">3</a></li>
						<li>4</li>
						<li>5</li>
						<li><a href="">▶</a></li>
					</ul>
				</div>					
				pager 추가 -->
				
				<div class="bottom">
					<c:if test='${not empty authUser}'>
						<a href="${pageContext.servletContext.contextPath}/board/write" id="new-book">글쓰기</a>
					</c:if>
				</div>				
			</div>
		</div>
		<c:import url='/WEB-INF/views/includes/navigation.jsp'>
			<c:param name="menu" value="board" />
		</c:import>
		<c:import url='/WEB-INF/views/includes/footer.jsp' />
	</div>
</body>
</html>