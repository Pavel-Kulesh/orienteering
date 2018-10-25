<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<c:set var="baseUrl" value="${pageContext.request.contextPath}" />
<header>
	<nav>
		<div class="nav-wrapper container">
			<ul class="left hide-on-med-and-down">
				<li><a href="${baseUrl}/" title="Home"><i
						class="large material-icons">home</i></a></li>
				<li><a href="${baseUrl}/news">News</a></li>
				<li><a href="${baseUrl}/event">Events</a></li>
				<li><a href="${baseUrl}/participant">Participants</a></li>
				<li><a href="${baseUrl}/map">Maps</a></li>
				<li><a href="${baseUrl}/link">Links</a></li>


				<sec:authentication var="user" property="principal" />

				<sec:authorize access="isAuthenticated()">


					<li><i class="large material-icons">account_circle</i></li>

					<sec:authorize access="isAuthenticated()">
						<li><sec:authentication property="userName" /></li>
						<li>-</li>
						<li><sec:authentication property="authorities" /></li>
					</sec:authorize>






					<li><a class="right" href="${baseUrl}/execute_logout"
						title="logout"> ${pageContext.request.userPrincipal.name} <i
							class="material-icons">arrow_forward</i></a></li>

				</sec:authorize>



				<sec:authorize access="isAnonymous()">
					<li><a href="${baseUrl}/login" title="login"><i
							class="large material-icons">account_circle</i></a></li>

					<li><a href="${baseUrl}/registration" title="registration"><i
							class="large material-icons">person_add</i></a></li>
				</sec:authorize>


			</ul>
		</div>
	</nav>
</header>