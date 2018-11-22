<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<c:set var="baseUrl" value="${contextPath}/route" />
<h4 class="header">List routes</h4>
<table class="bordered highlight">
	<tbody>
		<tr>
			<th>id</th>
			<th>name</th>
			<sec:authorize access="!isAnonymous()">
				<th><mytaglib:sort-link column="info" pageUrl="${baseUrl}">
						<i class="material-icons">description</i>
						<mytaglib:i18n key="news.info" />
					</mytaglib:sort-link></th>
				<th><i class="material-icons">access_time</i> <mytaglib:i18n
						key="news.created" /></th>
				<th><i class="material-icons">access_time</i> <mytaglib:i18n
						key="news.updated" /></th>
				<th></th>

			</sec:authorize>
			<th></th>
		</tr>
		<c:forEach var="news" items="${gridItem}" varStatus="loopCounter">
			<tr>
				<td><c:out value="${news.id}" /></td>
				<td><c:out value="${news.name}" /></td>
				<sec:authorize access="!isAnonymous()">
					<td><c:out value="${news.info}" /></td>
					<td><fmt:formatDate pattern="yyyy-MM-dd"
							value="${news.created}" /></td>
					<td><fmt:formatDate pattern="yyyy-MM-dd"
							value="${news.updated}" /></td>
				</sec:authorize>
				<td class="right"><a class="btn-floating"
					href="${baseUrl}/${news.id}"><i class="material-icons">info</i></a>

					<sec:authorize access="hasAnyRole('ADMIN')">
						<a class="btn-floating" href="${baseUrl}/${news.id}/edit"><i
							class="material-icons">edit</i></a>
						<a class="btn-floating red" href="${baseUrl}/${news.id}/delete"><i
							class="material-icons">delete</i></a>
					</sec:authorize></td>

			</tr>
		</c:forEach>
	</tbody>
</table>
<jspFragments:paging />
<sec:authorize access="hasAnyRole('ADMIN','ORGANIZER')">

	<a class="waves-effect waves-light btn right" href="${baseUrl}/add">Add route<i class="material-icons">add</i></a>
</sec:authorize>