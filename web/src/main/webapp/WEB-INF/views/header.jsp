<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<c:set var="baseUrl" value="${pageContext.request.contextPath}" />
<header>
	<nav>
		<div class="nav-wrapper container">
			<ul class="left hide-on-med-and-down">
				<li><a href="${baseUrl}/" title="Home"><mytaglib:i18n key="menu.home"/></a></li>
				<li><a href="${baseUrl}/news"><mytaglib:i18n key="menu.news"/></a></li>
				<li><a href="${baseUrl}/event"><mytaglib:i18n key="menu.event"></mytaglib:i18n></a></li>
				<li><a href="${baseUrl}/participant"><mytaglib:i18n key="menu.participant"></mytaglib:i18n></a></li>
				<li><a href="${baseUrl}/map"><mytaglib:i18n key="menu.map"></mytaglib:i18n></a></li>
				<li><a href="${baseUrl}/link"><mytaglib:i18n key="menu.link"></mytaglib:i18n></a></li>




				<sec:authorize access="isAuthenticated()">
					<li><i class="large material-icons">account_circle</i></li>
					<li><sec:authentication property="name" /> <sec:authentication
							property="surname" /> <sec:authentication property="role" /></li>



					<li><a class="right" href="${baseUrl}/execute_logout"
						title="logout"><sec:authentication property="email" /> <i
							class="material-icons">arrow_forward</i></a></li>

				</sec:authorize>




				<sec:authorize access="isAnonymous()">
					<li><a href="${baseUrl}/login" title="login"><i
							class="large material-icons">account_circle</i></a></li>

					<li><a href="${baseUrl}/registration" title="registration"><i
							class="large material-icons">person_add</i></a></li>
				</sec:authorize>

				<li><a href="${baseUrl}?language=ru">RU</a></li>
				<li><a href="${baseUrl}?language=en">EN</a></li>

			</ul>
		</div>
	</nav>
</header>