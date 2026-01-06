<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspHeader.jsp"%>

<!DOCTYPE html>
<html lang="ja">

<head>
  <meta charset="utf-8">
  <meta content="width=device-width, initial-scale=1.0" name="viewport">
  <meta name="robots" content="noindex, nofollow">
  <title>Login - Yamashita Portfolio</title>

  <!-- favicon -->
  <jsp:include page="/WEB-INF/favicon.jsp" />

  <!-- Password Symbols -->
  <link href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,400,0,0" rel="stylesheet">

  <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css">

</head>

<body>
  <!-- Header -->
  <header class="header">
    <div class="header-inner">
      <a class="site-title" href="${pageContext.request.contextPath}/">Yamashita Portfolio</a>
    </div>
  </header>

  <!-- Login Main -->
  <main class="login-wrapper">
    <div class="login-card">
      <h1 class="login-title">Login</h1>

      <!-- エラーメッセージ -->
      <c:if test="${not empty ERROR_MESSAGE_LIST}">
        <ul class="error-message font-color__orange mt-50" role="alert">
          <c:forEach var="message" items="${ERROR_MESSAGE_LIST}">
            <li><c:out value="${message}" /></li>
          </c:forEach>
        </ul>
      </c:if>

      <form id="loginForm" action="${pageContext.request.contextPath}/public/LoginServlet" method="post">

        <!-- User ID Input -->
        <div class="form-group">
          <label class="form-label" for="userId">User ID</label>
          <input class="form-input" id="userId" name="userId" type="text" placeholder="user01" required autocomplete="username">
        </div>

        <!-- Password Input -->
        <div class="form-group">
          <label class="form-label" for="password">Password</label>
          <input class="form-input" id="password" name="password" type="password" placeholder="••••••••" required autocomplete="current-password">
          <span id="togglePassword" class="material-symbols-outlined toggle-password">visibility</span>
        </div>

        <!-- Submit Button -->
        <button class="btn-submit" type="submit">Sign In</button>
      </form>
    </div>
  </main>

  <!-- Footer -->
  <footer class="footer">
    <div class="container">
      <p class="footer-copy">&copy; 2025 Yamashita Portfolio. All Rights Reserved.</p>
    </div>
  </footer>

  <!-- JavaScript -->
  <script src="${pageContext.request.contextPath}/static/js/login.js"></script>
</body>
</html>