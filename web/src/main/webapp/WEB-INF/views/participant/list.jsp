<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<c:set var="baseUrl" value="${contextPath}/participant" />
<h4 class="header">Participants</h4>
<table class="bordered highlight">
	<tbody>
		<tr>
			<th><mytaglib:sort-link column="id" pageUrl="${baseUrl}">id</mytaglib:sort-link></th>
			<th><mytaglib:sort-link column="name" pageUrl="${baseUrl}">name</mytaglib:sort-link></th>
			<th><mytaglib:sort-link column="surname" pageUrl="${baseUrl}">surname</mytaglib:sort-link></th>
			<sec:authorize access="!isAnonymous()">
				<th>phone</th>
				<th>city_id</th>
				<th>created</th>
				<th>updated</th>
			</sec:authorize>
			<th></th>
		</tr>
		<c:forEach var="participant" items="${listDTO.list}"
			varStatus="loopCounter">
			<tr>
				<td><c:out value="${participant.id}" /></td>
				<td><c:out value="${participant.name}" /></td>
				<td><c:out value="${participant.surname}" /></td>

				<sec:authorize access="!isAnonymous()">
					<td><c:out value="${participant.phone}" /></td>
					<td><c:out value="${participant.cityId}" /></td>
					<td><fmt:formatDate pattern="yyyy-MM-dd"
							value="${participant.created}" /></td>
					<td><fmt:formatDate pattern="yyyy-MM-dd"
							value="${participant.updated}" /></td>
				</sec:authorize>
				<td class="right"><a class="btn-floating"
					href="${baseUrl}/${participant.id}"><i class="material-icons">info</i></a>
					<sec:authorize access="hasAnyRole('ADMIN')">
						<a class="btn-floating" href="${baseUrl}/${participant.id}/edit"><i
							class="material-icons">edit</i></a>
						<a class="btn-floating red"
							href="${baseUrl}/${participant.id}/delete"><i
							class="material-icons">delete</i></a>
					</sec:authorize></td>

			</tr>
		</c:forEach>
	</tbody>
</table>
<jspFragments:paging />


<sec:authorize access="hasAnyRole('ADMIN')">

	<a class="waves-effect waves-light btn right" href="${baseUrl}/add"><i
		class="material-icons">add</i></a>
</sec:authorize>