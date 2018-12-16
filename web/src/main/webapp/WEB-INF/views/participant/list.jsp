<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<c:set var="baseUrl" value="${contextPath}/participant" />
<h4 class="header"><mytaglib:i18n key="participant.header" /></h4>
<table class="bordered highlight">
	<tbody>
		<tr>
			<sec:authorize access="hasRole('ADMIN')">
				<th><mytaglib:sort-link column="id" pageUrl="${baseUrl}">
						<i class="material-icons">bubble_chart</i>id</mytaglib:sort-link></th>
			</sec:authorize>
			<th><mytaglib:sort-link column="name" pageUrl="${baseUrl}">
					<i class="material-icons">directions_run</i><mytaglib:i18n key="participant.name" /></mytaglib:sort-link></th>
			<th><mytaglib:sort-link column="surname" pageUrl="${baseUrl}">
					<i class="material-icons">child_care</i><mytaglib:i18n key="participant.surname" /></mytaglib:sort-link></th>
			<sec:authorize access="hasRole('ADMIN')">
				<th><mytaglib:sort-link column="phone" pageUrl="${baseUrl}"><i class="material-icons">contact_phone</i><mytaglib:i18n key="participant.phone" /></mytaglib:sort-link></th>
				<th><mytaglib:sort-link column="city" pageUrl="${baseUrl}"><i class="material-icons">location_city</i><mytaglib:i18n key="participant.city" /></mytaglib:sort-link></th>
				<th><mytaglib:sort-link column="created" pageUrl="${baseUrl}"><mytaglib:i18n key="participant.created" /></mytaglib:sort-link></th>
			</sec:authorize>
			<th></th>
		</tr>
		<c:forEach var="participant" items="${gridItem}"
			varStatus="loopCounter">
			<tr>
				<sec:authorize access="hasRole('ADMIN')">
					<td><c:out value="${participant.id}" /></td>
				</sec:authorize>
				<td><c:out value="${participant.name}" /></td>
				<td><c:out value="${participant.surname}" /></td>

				<sec:authorize access="hasRole('ADMIN')">
					<td><c:out value="${participant.phone}" /></td>
					<td><c:out value="${participant.cityName}" /></td>
					<td><fmt:formatDate pattern="yyyy-MM-dd"
							value="${participant.created}" /></td>

				</sec:authorize>
				<td class="right"><a class="btn-floating"
					href="${baseUrl}/${participant.id}"><i class="material-icons">info</i></a>



					<c:if test="${participant.statusVisible}">
						<a class="btn-floating orange" href="${baseUrl}/${participant.id}/edit"><i
							class="material-icons">edit</i></a>
						<a class="btn-floating red"
							href="${baseUrl}/${participant.id}/delete"><i
							class="material-icons">delete</i></a>
					</c:if></td>

			</tr>
		</c:forEach>
	</tbody>
</table>
<jspFragments:paging />


<sec:authorize access="hasRole('ADMIN')">

	<a class="waves-effect waves-light btn right" href="${baseUrl}/add"><i
		class="material-icons">add</i></a>
</sec:authorize>
