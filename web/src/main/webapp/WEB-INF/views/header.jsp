<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<c:set var="baseUrl" value="${pageContext.request.contextPath}" />
<header>
    <ul id="languages" class="dropdown-content">
        <li><a href="${baseUrl}?language=ru"><mytaglib:i18n key="menu.ru"></mytaglib:i18n></a></li>
        <li><a href="${baseUrl}?language=en"><mytaglib:i18n key="menu.en"></mytaglib:i18n></a></li>
    </ul>
    <nav>
        <div class="nav-wrapper">
            <ul class="left hide-on-med-and-down">
                <li><a href="${baseUrl}/news"><mytaglib:i18n key="menu.news" /></a></li>
                <li><a href="${baseUrl}/event"><mytaglib:i18n key="menu.event"></mytaglib:i18n></a></li>
                <li><a href="${baseUrl}/participant"><mytaglib:i18n key="menu.participant"></mytaglib:i18n></a></li>
                <li><a href="${baseUrl}/map"><mytaglib:i18n key="menu.map"></mytaglib:i18n></a></li>
                <sec:authorize access="isAuthenticated()">
                    <li><a href="${baseUrl}/route"><mytaglib:i18n key="menu.route"></mytaglib:i18n></a></li>
                    
                       <li><a href="${baseUrl}/participant/<sec:authentication
                                property="id" />/edit">TODO</a></li>
                </sec:authorize>
                
                
            </ul>
            <ul class="right">
                <li><a class="dropdown-trigger" href="#!" data-target="languages">langauges<i class="material-icons right">arrow_drop_down</i></a></li>
                <sec:authorize access="isAuthenticated()">
                    <li><a href="${baseUrl}/execute_logout" title="logout"> <mytaglib:i18n key="menu.info.logout"></mytaglib:i18n>(<sec:authentication
                                property="email" /> <sec:authentication property="role" />)
                    </a></li>
                </sec:authorize>
                <sec:authorize access="isAnonymous()">
                    <li><a href="${baseUrl}/login" title="login"> <mytaglib:i18n key="menu.login"></mytaglib:i18n></a></li>
                    <li><a href="${baseUrl}/registration" title="registration"><mytaglib:i18n key="menu.login.registration"></mytaglib:i18n></a></li>
                </sec:authorize>
            </ul>
        </div>
    </nav>
</header>