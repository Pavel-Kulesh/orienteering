<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="baseUrl" value="${pageContext.request.contextPath}" />
<header>
	<nav>
		<div class="nav-wrapper container">
			<ul class="left hide-on-med-and-down">
				<li><a href="${baseUrl}/">Home</a></li>
				<li><a href="${baseUrl}/news">News</a></li>
				<li><a href="${baseUrl}/model">Models</a></li>
				<li><a href="${baseUrl}/engine">Engines</a></li>
				<li><a href="${baseUrl}/car">Cars</a></li>
			</ul>
		</div>
	</nav>
</header>