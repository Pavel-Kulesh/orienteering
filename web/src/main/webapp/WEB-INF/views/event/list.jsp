<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>

<c:set var="baseUrl" value="${contextPath}/event" />
<h4 class="header">Event</h4>
<table class="bordered highlight">
	<tbody>
		<tr>
			<th><mytaglib:sort-link column="id" pageUrl="${baseUrl}">id</mytaglib:sort-link></th>

			<th><mytaglib:sort-link column="name" pageUrl="${baseUrl}">name</mytaglib:sort-link></th>

			<sec:authorize access="!isAnonymous()">
				<th>info</th>
				<th>date</th>

				<th>creator_id</th>
				<th>country_id</th>
				<th>created</th>
				<th>updated</th>
				<th></th>
			</sec:authorize>
		</tr>
		<c:forEach var="event" items="${gridItem}" varStatus="loopCounter">
			<tr>
				<td><c:out value="${event.id}" /></td>
				<td><c:out value="${event.name}" /></td>
				<sec:authorize access="!isAnonymous()">
					<td><c:out value="${event.info}" /></td>

					<td><fmt:formatDate pattern="yyyy-MM-dd" value="${event.date}" /></td>

					<td><c:out value="${event.creatorId}" /></td>
					<td><c:out value="${event.countryId}" /></td>

					<td><fmt:formatDate pattern="yyyy-MM-dd"
							value="${event.created}" /></td>
					<td><fmt:formatDate pattern="yyyy-MM-dd"
							value="${event.updated}" /></td>
				</sec:authorize>
				<td class="right"><a class="btn-floating"
					href="${baseUrl}/${event.id}"><i class="material-icons">info</i></a>
					<sec:authorize access="!isAnonymous()">
						<a class="btn-floating" href="${baseUrl}/${event.id}/edit"><i
							class="material-icons">edit</i></a>
						<a class="btn-floating red" href="${baseUrl}/${event.id}/delete"><i
							class="material-icons">delete</i></a>
					</sec:authorize></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<sec:authorize access="hasAnyRole('ADMIN')">

	<a class="waves-effect waves-light btn right" href="${baseUrl}/add"><i
		class="material-icons">add</i></a>
</sec:authorize>