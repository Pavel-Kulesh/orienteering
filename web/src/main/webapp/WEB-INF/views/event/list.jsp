<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<c:set var="baseUrl" value="${contextPath}/event" />
<h4 class="header">Event</h4>
<table class="bordered highlight">
	<tbody>
		<tr>
			<th><mytaglib:sort-link column="id" pageUrl="${baseUrl}">
					<i class="material-icons">filter_vintage</i>id</mytaglib:sort-link></th>

			<th><mytaglib:sort-link column="name" pageUrl="${baseUrl}">
					<i class="material-icons">assignment_ind</i>name</mytaglib:sort-link></th>

			<th><mytaglib:sort-link column="type" pageUrl="${baseUrl}">type</mytaglib:sort-link></th>

			<th><mytaglib:sort-link column="date" pageUrl="${baseUrl}">
					<i class="material-icons">access_time</i>date</mytaglib:sort-link></th>
			<sec:authorize access="!isAnonymous()">
				<th><i class="material-icons">description</i>info</th>
				<th><mytaglib:sort-link column="customer_id" pageUrl="${baseUrl}">customer_id</mytaglib:sort-link></th>
				<th><mytaglib:sort-link column="country" pageUrl="${baseUrl}"><i class="material-icons">public</i>country</mytaglib:sort-link></th>
				<th><mytaglib:sort-link column="created" pageUrl="${baseUrl}"><i class="material-icons">query_builder</i>created</mytaglib:sort-link></th>
				<th><mytaglib:sort-link column="updated" pageUrl="${baseUrl}"><i class="material-icons">access_time</i>updated</mytaglib:sort-link></th>
				<th></th>
			</sec:authorize>
		</tr>
		<c:forEach var="event" items="${gridItem}" varStatus="loopCounter">
			<tr>
				<td><c:out value="${event.id}" /></td>
				<td><c:out value="${event.name}" /></td>

				<td><c:out value="${event.type }" /></td>
				<td><fmt:formatDate pattern="yyyy-MM-dd" value="${event.date}" />


				</td>
				<sec:authorize access="!isAnonymous()">


					<td><c:out value="${event.info}" /></td>

					<td><c:out value="${event.customerId}" /></td>
					<td><c:out value="${event.countryName}" /></td>

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

<jspFragments:paging />
<sec:authorize access="hasAnyRole('ADMIN')">

	<a class="waves-effect waves-light btn right" href="${baseUrl}/add"><i
		class="material-icons">add</i></a>
</sec:authorize>