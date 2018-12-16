<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<c:set var="baseUrl" value="${pageContext.request.contextPath}" />
<header>
	<nav>
		<div class="nav-wrapper container">
			<ul class="left hide-on-med-and-down">
				<li><a href="${baseUrl}/news"><mytaglib:i18n
							key="menu.news" /></a></li>
				<li><a href="${baseUrl}/event"><mytaglib:i18n
							key="menu.event"></mytaglib:i18n></a></li>
				<li><a href="${baseUrl}/participant"><mytaglib:i18n
							key="menu.participant"></mytaglib:i18n></a></li>
				<li><a href="${baseUrl}/map"><mytaglib:i18n key="menu.map"></mytaglib:i18n></a></li>
				<sec:authorize access="isAuthenticated()">
					<li><a href="${baseUrl}/route"><mytaglib:i18n
								key="menu.route"></mytaglib:i18n></a></li>
				</sec:authorize>
				
				<sec:authorize access="isAuthenticated()">

					<li><a class='dropdown-trigger btn' data-target='dropdown1'><mytaglib:i18n key="menu.info"></mytaglib:i18n></a>

						<ul id='dropdown1' class='dropdown-content'>
							<li><mytaglib:i18n key="menu.info.name"></mytaglib:i18n>: <sec:authentication property="name" /></li>
							<li><mytaglib:i18n key="menu.info.surname"></mytaglib:i18n>: <sec:authentication property="surname" /></li>
							<li class="divider" tabindex="-1"></li>
							<li><mytaglib:i18n key="menu.info.role"></mytaglib:i18n>: <sec:authentication property="role" /></li>
							<li><mytaglib:i18n key="menu.info.email"></mytaglib:i18n>: <sec:authentication property="email" /></li>
							<li><a href="${baseUrl}/execute_logout" title="logout"><i
									class="material-icons">arrow_forward</i><mytaglib:i18n key="menu.info.logout"></mytaglib:i18n></a></li>
						</ul></li>


				</sec:authorize>



				<sec:authorize access="isAnonymous()">


					<li><a class='dropdown-trigger btn' data-target='dropdown1'><mytaglib:i18n key="menu.login"></mytaglib:i18n></a>


						<ul id='dropdown1' class='dropdown-content'>
							<li><a href="${baseUrl}/login" title="login"><i
									class="large material-icons">account_circle</i><mytaglib:i18n key="menu.login"></mytaglib:i18n></a></li>
							<li><a href="${baseUrl}/registration" title="registration"><i
									class="large material-icons">person_add</i><mytaglib:i18n key="menu.login.registration"></mytaglib:i18n></a></li>

						</ul></li>

				</sec:authorize>

				<li><a  href="${baseUrl}?language=ru"><mytaglib:i18n key="menu.ru"></mytaglib:i18n></a></li>
				<li><a href="${baseUrl}?language=en"><mytaglib:i18n key="menu.en"></mytaglib:i18n></a></li>
			</ul>
		</div>
	</nav>
</header>