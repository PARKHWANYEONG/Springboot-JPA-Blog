<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>
<div class="container">
    <button class="btn btn-secondary" onclick="history.back()">돌아가기</button>
    <c:if test="${board.user.id == principal.user.id}">
        <a href="/board/${board.id}/updateForm" class="btn btn-info" role="button">수정</a>
        <button id="btn-delete" class="btn btn-danger">삭제</button>
    </c:if>
    <br/><br/>
      <div>
        게시글번호: <span id="id"><i>${board.id}</i></span>
      </div>
      <br/>
      <div>
        작성자: <span><i>${board.user.username} </i></span>
      </div>
      <br/>
      <div>
        <h3>${board.title}</h3>
      </div>
      <div>
        <div>${board.content}</div>
      </div>
      <div class="card">
        <input type="hidden" id="userId" value="${principal.user.id}">
        <div class="card-body"><textarea id="comment-content" class="form-control" rows="1"></textarea></div>
        <div class="card-footer"><button type="button" id="btn-comment-save" class="btn btn-primary">등록</button></div>
      </div>
        <br>
       <div class="card">
            <div class="card-header">댓글 리스트</div>
                <ul class="list-group">
                <c:forEach var="comment" items="${board.comments}">
                  <li id="comment-${comment.id}" class="list-group-item d-flex justify-content-between">
                      <div>${comment.content}</div>
                      <div class="d-flex">
                        <div>작성자: ${comment.user.username} &nbsp;</div>
                        <c:if test="${comment.user.id == principal.user.id}">
                        <button onclick="index.commentDelete(${board.id},${comment.id})" class="badge">삭제</button>
                        </c:if>
                      </div>
                  </li>
                </c:forEach>
                </ul>
       </div>
</div>
<script src="/js/board.js"></script>
<%@ include file="../layout/footer.jsp"%>
