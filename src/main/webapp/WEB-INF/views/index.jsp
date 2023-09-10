<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="layout/header.jsp"%>
<%-- 테이블 컨테이너 --%>
<div class="container">
  <table class="table table-hover">
    <thead>
      <tr>
        <th>게시글번호</th>
        <th>제목</th>
        <th>작성자</th>
        <th>작성일</th>
      </tr>
    </thead>
    <tbody>
       <c:forEach var="board" items="${boards.content}">
          <tr>
            <td><a href="/board/${board.id}">${board.id}</a></td>
            <td><a href="/board/${board.id}">${board.title}</a></td>
            <td>${board.user.username}</td>
            <td>${board.createdDate}</td>
          </tr>
       </c:forEach>
    </tbody>
  </table>
</div>
<ul class="pagination justify-content-center">
  <li class="page-item <c:if test="${boards.number == 0}">disabled</c:if>">
    <a class="page-link" href="?page=${boards.number-1}">Previous</a>
  </li>
  <li class="page-item <c:if test="${boards.number ==  boards.totalPages - 1}">disabled</c:if>">
    <a class="page-link" href="?page=${boards.number+1}">Next</a>
   </li>
</ul>
<%@ include file="layout/footer.jsp"%>

