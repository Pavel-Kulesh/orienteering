<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="baseUrl" value="${pageContext.request.contextPath}" />
<header>
	<nav>
		<div class="nav-wrapper container">
			<ul class="left hide-on-med-and-down">
				<li><a href="${baseUrl}/">Home</a></li>
				<li><a href="${baseUrl}/news">News</a></li>
				<li><a href="${baseUrl}/events">Events</a></li>
				<li><a href="${baseUrl}/participants">Participants</a></li>
				<li><a href="${baseUrl}/maps">Maps</a></li>
				<li><a href="${baseUrl}/links">Links</a></li>
			</ul>
		</div>
	</nav>
</header>