<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspHeader.jsp"%>

<c:set var="newline" value="<%=\"\n\"%>" />

<!DOCTYPE html>
<html lang="ja">

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Yamashita Portfolio</title>

<!-- favicon -->
<jsp:include page="/WEB-INF/favicon.jsp" />

<link rel="stylesheet"
  href="${cp}/static/css/style.css">
</head>

<body>
  <!-- Header -->
  <header class="header">
    <div class="header-inner">
      <p class="site-title">Yamashita Portfolio</p>
      <nav class="nav">
        <a href="javascript:void(0)" data-scroll="about">About</a> <a
          href="javascript:void(0)" data-scroll="training">Training</a>
        <a href="javascript:void(0)" data-scroll="works">Works</a>
      </nav>
    </div>
  </header>

  <!-- Hero -->
  <section class="hero">
    <h1 class="hero-title">Portfolio</h1>
    <p class="hero-text">
      職業訓練で学んだWebアプリケーション開発の基礎と、<br class="pc-only">これまでのコーディング経験を活かし、<br
        class="pc-only">丁寧で分かりやすい実装ができるプログラマーを目指しています。
    </p>
  </section>

  <!-- About -->
  <section id="about" class="section">
    <div class="container two-column">
      <div class="column-image">
        <img
          src="${cp}/static/img/about-beach01.webp"
          alt="About Image">
      </div>
      <div class="column-text">
        <h2>About</h2>
        <p>Webコーダーとして約1年5ヶ月、主に静的ページのコーディングを担当していました。HTMLとCSSを中心に、見やすさや構造を意識したコーディングを行ってきました。</p>
        <p>現在は職業訓練にてJavaを中心としたWebアプリケーション開発を学び、設計から実装まで一連の流れを経験しています。</p>
        <div class="skills-block">
          <h3>Skills</h3>
          <ul class="skills-list">
            <li>HTML / CSS</li>
            <li>Java / Servlet / JSP</li>
            <li>例外処理設計</li>
            <li>MVCモデル</li>
            <li>PostgreSQL</li>
            <li>Git</li>
          </ul>
        </div>
      </div>
    </div>
  </section>

  <!-- Training -->
  <section id="training" class="section bg-gray">
    <div class="container two-column reverse">
      <div class="column-image">
        <img
          src="${cp}/static/img/training-laptop01.webp"
          alt="Training Image">
      </div>
      <div class="column-text">
        <h2>Training</h2>
        <p>職業訓練では、Javaを中心としたWebアプリケーション開発を学びました。基本設計・詳細設計を行い、CRUD機能を持つアプリケーションをServlet / JSP / PostgreSQL を用いて実装しました。</p>
      </div>
    </div>
  </section>

  <!-- Works -->
  <section id="works" class="section">
    <div class="container">
      <h2 class="section-title">Works</h2>
      <c:choose>
        <c:when test="${empty worksList}">
          <p>Works が見つかりませんでした</p>
        </c:when>
        <c:otherwise>
          <div class="works-grid">
            <c:forEach var="work" items="${worksList}"
              varStatus="status">
              <div class="work-card" data-modal="modal-${status.index}">
                <div class="work-thumb">
                  <img
                    src="<c:out value="${cp}/${work.imgPath}" />"
                    alt="<c:out value='${work.title}' />"
                    class="work-image">
                </div>
                <h3>
                  <c:out value="${work.title}" />
                </h3>
                <p>${work.languages}</p>
                <p class="date">
                  <c:out value="${work.workDate}" />
                </p>
              </div>
            </c:forEach>
          </div>
        </c:otherwise>
      </c:choose>
    </div>
  </section>

  <!-- Modal -->
  <c:if test="${not empty worksList}">
    <c:forEach var="work" items="${worksList}" varStatus="status">
      <dialog class="modal" id="modal-${status.index}"
        aria-labelledby="modal-title-${status.index}">
      <div class="modal-content">
        <button class="modal-close" aria-label="閉じる">&times;</button>
        <div class="modal-body">
          <!-- 左：画像 -->
          <div class="modal-image">
            <c:choose>
              <%-- 文字列に "movie-target" が含まれているか判定 --%>
              <c:when
                test="${fn:contains(work.imgPath, 'movie-target')}">
                <div class="video-container">
                <video src="${cp}/${fn:replace(work.imgPath, '.webp', '.mp4')}"
  poster="${cp}/${work.imgPath}"
  autoplay muted loop playsinline
  class="bg-video">
                  <img src="<c:out value="${cp}/${work.imgPath}" />"
                    alt="作品画像"
                    class="work-image">
                </video>
                </div>
              </c:when>

              <%-- 含まれていない場合 --%>
              <c:otherwise>
                <img
                  src="<c:out value="${cp}/${work.imgPath}" />"
                  alt="<c:out value='${work.title}' />"
                  class="work-image">
              </c:otherwise>
            </c:choose>
          </div>
          <!-- 右：説明 -->
          <div class="modal-text">
            <!-- 作品名 -->
            <h3 class="modal-title"><c:out value="${work.title}" /></h3>

            <!-- 使用言語 -->
            <p class="modal-languages"><c:out value="${work.languages}" /></p>

            <!-- 説明 -->
            <p class="modal-description"><c:out value="${work.description}" /></p>

            <!-- 担当 -->
            <c:if test="${not empty work.position}">
              <h4 class="modal-subtitle">担当</h4>
              <p class="modal-paragraph"><c:out value="${work.position}" /></p>
            </c:if>

            <!-- 開発条件 -->
            <c:if test="${not empty work.conditions}">
              <h4 class="modal-subtitle">開発条件</h4>
              <p class="modal-paragraph"><c:out value="${fn:replace(work.conditions, '__BR__', newline)}" /></p>
            </c:if>

            <!-- 取り組み方 -->
            <c:if test="${not empty work.approach}">
              <h4 class="modal-subtitle">取り組み方</h4>
              <p class="modal-paragraph"><c:out value="${fn:replace(work.approach, '__BR__', newline)}" /></p>
            </c:if>

            <!-- 学び -->
            <c:if test="${not empty work.learning}">
              <h4 class="modal-subtitle">学び</h4>
              <p class="modal-paragraph"><c:out value="${fn:replace(work.learning, '__BR__', newline)}" /></p>
            </c:if>

            <!-- リンク -->
            <div class="modal-links">
              <c:if test="${not empty work.gitUrl}">
                <a href="<c:out value="${work.gitUrl}" />"
                  target="_blank" rel="noopener noreferrer">GitHub</a>
              </c:if>
              <c:if test="${not empty work.demoUrl}">
                <a href="<c:out value="${work.demoUrl}" />"
                  target="_blank" rel="noopener noreferrer">Demo</a>
              </c:if>
            </div>

            <!-- デモの補足 -->
            <c:if test="${not empty work.demoInformation}">
              <div class="demo-login-info">
                <p class="demo-login-title"><c:out value="${work.demoInformation}" /></p>
                <p class="demo-login-text"><c:out value="${fn:replace(work.demoLoginCredentials, '__BR__', newline)}" /></p>
              </div>
            </c:if>
          </div>

        </div>
      </div>
      </dialog>
    </c:forEach>
  </c:if>

  <!-- Footer -->
  <footer class="footer">
    <div class="container">
      <p class="footer-msg">Looking forward to the day we can work
        together.</p>
      <p class="footer-copy">&copy; 2025 Yamashita Portfolio. All
        Rights Reserved.</p>
    </div>
  </footer>

  <script
    src="${cp}/static/js/portfolio.js"></script>
</body>

</html>