<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>
<div class="container">
    <form>
       <input type="hidden" id="id" value="${principal.user.id}">
      <div class="form-group">
        <label for="username">Username</label>
        <input type="text" id="username" class="form-control" value="${principal.user.username}" placeholder="Enter username" readonly>
      </div>
      <c:if test="${empty principal.user.oauth}">
            <div class="form-group">
              <label for="password">Password</label>
              <input type="password" id="password" class="form-control" placeholder="Enter password">
            </div>
            <div class="form-group">
                    <label for="email">Email</label>
                    <input type="email" id="email" class="form-control" value="${principal.user.email}" placeholder="Enter email">
            </div>
      </c:if>
    </form>
    <button id="btn-update" class="btn btn-primary">수정완료</button>
</div>

<script src="/js/user.js"></script>
<%@ include file="../layout/footer.jsp"%>

