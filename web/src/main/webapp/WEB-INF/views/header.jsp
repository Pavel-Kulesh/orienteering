<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<c:set var="baseUrl" value="${pageContext.request.contextPath}" />
<header>
	<nav>
		<div class="nav-wrapper container">
			<ul class="left hide-on-med-and-down">
				<li><a href="${baseUrl}/" title="Home"><mytaglib:i18n
							key="menu.home" /></a></li>
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
				<li><a href="${baseUrl}/link"><mytaglib:i18n
							key="menu.link"></mytaglib:i18n></a></li>




				<sec:authorize access="isAuthenticated()">

					<li><a class='dropdown-trigger btn' data-target='dropdown1'>Info</a>


						<ul id='dropdown1' class='dropdown-content'>
							<li>Name: <sec:authentication property="name" /></li>
							<li>Surname: <sec:authentication property="surname" /></li>
							<li class="divider" tabindex="-1"></li>
							<li>Role: <sec:authentication property="role" /></li>
							<li>Email: <sec:authentication property="email" /></li>
							<li><a href="${baseUrl}/execute_logout" title="logout"><i
									class="material-icons">arrow_forward</i>logout</a></li>
						</ul></li>


				</sec:authorize>



				<sec:authorize access="isAnonymous()">


					<li><a class='dropdown-trigger btn' data-target='dropdown1'>Login</a>


						<ul id='dropdown1' class='dropdown-content'>
							<li><a href="${baseUrl}/login" title="login"><i
									class="large material-icons">account_circle</i>Login</a></li>
							<li><a href="${baseUrl}/registration" title="registration"><i
									class="large material-icons">person_add</i>Registration</a></li>

						</ul></li>

				</sec:authorize>

				<li><a href="${baseUrl}?language=ru">RU</a></li>
				<li><a href="${baseUrl}?language=en">EN</a></li>

			</ul>
		</div>
	</nav>
</header>