<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<c:set var="baseUrl" value="${contextPath}/map" />
<h4 class="header">Map</h4>
<table class="bordered highlight">
	<tbody>
		<tr>
			<th><mytaglib:sort-link column="id" pageUrl="${baseUrl}">id</mytaglib:sort-link></th>
			<th><mytaglib:sort-link column="name" pageUrl="${baseUrl}">name</mytaglib:sort-link></th>
			<th><mytaglib:sort-link column="user_id" pageUrl="${baseUrl}">user_id</mytaglib:sort-link></th>
			<th></th>
		</tr>

		<c:forEach var="map" items="${gridItem}" varStatus="loopCounter">
			<tr>
				<td><c:out value="${map.id}" /></td>
				<td><c:out value="${map.name}" /></td>
				<td><c:out value="${map.userId}" /></td>

				<td class="right"><a class="btn-floating"
					href="${baseUrl}/${map.id}"><i class="material-icons">info</i></a>
					<sec:authorize access="!isAnonymous()">
						<a class="btn-floating" href="${baseUrl}/${map.id}/edit"><i
							class="material-icons">edit</i></a>
						<a class="btn-floating red" href="${baseUrl}/${map.id}/delete"><i
							class="material-icons">delete</i></a>
					</sec:authorize></td>

			</tr>
		</c:forEach>
	</tbody>
</table>
<jspFragments:paging />
<sec:authorize access="hasAnyRole('ADMIN','ORGANIZER')">

	<a class="waves-effect waves-light btn right" href="${baseUrl}/add"><i
		class="material-icons">add</i></a>
</sec:authorize>