<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
	
<c:set var="baseUrl" value="${contextPath}/news" />
<h4 class="header">
	<mytaglib:i18n key="news.header" />
</h4>
<br/>
<div class="row">
	<div class="col s12 right-align">
		<span style="margin-right: 20px"><mytaglib:sort-link column="name" pageUrl="${baseUrl}"><i class="material-icons">assignment_ind</i><mytaglib:i18n key="news.name" /></mytaglib:sort-link></span>
		
		<span><mytaglib:sort-link column="created" pageUrl="${baseUrl}"><i class="material-icons">access_time</i><mytaglib:i18n key="news.created" /></mytaglib:sort-link></span>
	</div>
</div>


<div class="row">
	<c:forEach var="news" items="${gridItem}" varStatus="loopCounter">
		<div class="col s12">
			<div class="card hoverable" style="border-radius: 25px; border: 2px solid #4fc3f7">
				<div class="card-content center-align">
					<c:set var="newsInfo" value="${fn:substring(news.info, 0, 150)}" />
					<span class="card-title">${news.name}</span>
					<span><fmt:formatDate pattern="yyyy-MM-dd" value="${news.created}" /></span>
					<p style="margin-top: 16px">${newsInfo}...</p>
				</div>
				<div class="card-action center-align" style="border-radius: 0 0 25px 25px">
					<a class="btn-floating btn-small" href="${baseUrl}/${news.id}"><i class="material-icons">info</i></a>
					<sec:authorize access="hasRole('ADMIN')">
						<a class="btn-floating btn-small orange" href="${baseUrl}/${news.id}/edit"><i class="material-icons">edit</i></a>
						<a class="btn-floating btn-small red" href="${baseUrl}/${news.id}/delete"><i class="material-icons">delete</i></a>
					</sec:authorize>
				</div>
			</div>
		</div>
	</c:forEach>
</div>








<%-- <table class="bordered highlight">
	<tbody>
		<tr>
			<sec:authorize access="hasRole('ADMIN')">
				<th><mytaglib:sort-link column="id" pageUrl="${baseUrl}">
						<i class="material-icons">filter_vintage</i>id</mytaglib:sort-link></th>
			</sec:authorize>
			<th><mytaglib:sort-link column="name" pageUrl="${baseUrl}">
					<i class="material-icons">assignment_ind</i>
					<mytaglib:i18n key="news.name" />
				</mytaglib:sort-link></th>
			<th><mytaglib:sort-link column="created" pageUrl="${baseUrl}">
					<i class="material-icons">access_time</i>
					<mytaglib:i18n key="news.created" />
				</mytaglib:sort-link></th>
			<sec:authorize access="hasRole('ADMIN')">

				<th><i class="material-icons">access_time</i> <mytaglib:i18n
						key="news.updated" /></th>
				<th></th>

			</sec:authorize>
			<th></th>
		</tr>
		
			<c:forEach var="news" items="${gridItem}" varStatus="loopCounter">
			<tr>
				<sec:authorize access="hasRole('ADMIN')">
					<td><c:out value="${news.id}" /></td>
				</sec:authorize>
				<td><c:out value="${news.name}" /></td>
				<td><fmt:formatDate pattern="yyyy-MM-dd"
						value="${news.created}" /></td>
				<sec:authorize access="hasRole('ADMIN')">
					<td><fmt:formatDate pattern="yyyy-MM-dd"
							value="${news.updated}" /></td>
				</sec:authorize>
				<td class="right"><a class="btn-floating"
					href="${baseUrl}/${news.id}"><i class="material-icons">info</i></a>

					<sec:authorize access="hasRole('ADMIN')">
						<a class="btn-floating orange" href="${baseUrl}/${news.id}/edit"><i
							class="material-icons">edit</i></a>
						<a class="btn-floating red" href="${baseUrl}/${news.id}/delete"><i
							class="material-icons">delete</i></a>
					</sec:authorize></td>
			</tr>
		</c:forEach>
	</tbody>
</table> --%>

<jspFragments:paging />
<sec:authorize access="hasRole('ADMIN')">

	<a class="waves-effect waves-light btn right" href="${baseUrl}/add"><mytaglib:i18n
			key="news.add" /><i class="material-icons">add</i></a>
</sec:authorize>
