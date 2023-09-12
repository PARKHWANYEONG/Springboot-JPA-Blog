<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>
<div class="container">
    <form action="/auth/loginProc" method="post">
      <div class="form-group">
        <label for="username">Username</label>
        <input type="text" class="form-control" placeholder="Enter username" id="username" name="username" required>
      </div>
      <div class="form-group">
        <label for="password">Password</label>
        <input type="password" class="form-control" placeholder="Enter password" id="password" name="password">
      </div>
    <button class="btn btn-primary">로그인</button>
    <a href="https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=6ad49cc974693084d53d706db9a04af3&redirect_uri=http://localhost:8000/auth/kakao/callback"><img height="38px" src="/image/kakao_login_button.png"/></a>
    </form>
</div>

<%@ include file="../layout/footer.jsp"%>

