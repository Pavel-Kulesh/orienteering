<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<c:set var="baseUrl" value="${pageContext.request.contextPath}" />
<header>
	<nav style="background-color: #999900;">
		<div class="nav-wrapper container">
			<ul class="left hide-on-med-and-down">
				<li><a href="${baseUrl}/">Home</a></li>
				<li><a href="${baseUrl}/news">News</a></li>
				<li><a href="${baseUrl}/events">Events</a></li>
				<li><a href="${baseUrl}/participants">Participants</a></li>
				<li><a href="${baseUrl}/maps">Maps</a></li>
				<li><a href="${baseUrl}/links">Links</a></li>
				<sec:authorize access="!isAnonymous()">
					<a class="right" href="${baseUrl}/execute_logout" title="logout"><i
						class="material-icons">arrow_forward</i></a>
				</sec:authorize>
			</ul>
		</div>
	</nav>
</header>