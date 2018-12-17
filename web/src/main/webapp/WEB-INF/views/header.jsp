<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<c:set var="baseUrl" value="${pageContext.request.contextPath}" />
<header>
    <ul id="languages" class="dropdown-content">
        <li><a href="${baseUrl}?language=ru"><mytaglib:i18n key="menu.ru"/></a></li>
        <li><a href="${baseUrl}?language=en"><mytaglib:i18n key="menu.en"/></a></li>
    </ul>
    <nav>
        <div class="nav-wrapper">
            <ul class="left hide-on-med-and-down">
                <li><a href="${baseUrl}/news"><mytaglib:i18n key="menu.news" /></a></li>
                <li><a href="${baseUrl}/event"><mytaglib:i18n key="menu.event"/></a></li>
                <li><a href="${baseUrl}/participant"><mytaglib:i18n key="menu.participant"/></a></li>
                <li><a href="${baseUrl}/map"><mytaglib:i18n key="menu.map"/></a></li>
                <sec:authorize access="isAuthenticated()">
                    <li><a href="${baseUrl}/route"><mytaglib:i18n key="menu.route"/></a></li>
                    
                       <li><a href="${baseUrl}/participant/<sec:authentication
                                property="id" />/edit"><mytaglib:i18n key="menu.personal"/></a></li>
                </sec:authorize>
                
                
            </ul>
            <ul class="right">
                <li><a class="dropdown-trigger" href="#!" data-target="languages"><mytaglib:i18n key="menu.languages"/><i class="material-icons right">arrow_drop_down</i></a></li>
                <sec:authorize access="isAuthenticated()">
                    <li><a href="${baseUrl}/execute_logout" title="logout"> <mytaglib:i18n key="menu.info.logout"/>(<sec:authentication
                                property="email" /> <sec:authentication property="role" />)
                    </a></li>
                </sec:authorize>
                <sec:authorize access="isAnonymous()">
                    <li><a href="${baseUrl}/login" title="login"> <mytaglib:i18n key="menu.login"/></a></li>
                    <li><a href="${baseUrl}/registration" title="registration"><mytaglib:i18n key="menu.login.registration"/></a></li>
                </sec:authorize>
            </ul>
        </div>
    </nav>
</header>