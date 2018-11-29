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
			<th><mytaglib:sort-link column="id" pageUrl="${baseUrl}">
					<i class="material-icons">filter_vintage</i>id</mytaglib:sort-link></th>
			<th><mytaglib:sort-link column="name" pageUrl="${baseUrl}">
					<i class="material-icons">assignment_ind</i>
					Name
				</mytaglib:sort-link></th>
			<sec:authorize access="hasAnyRole('ADMIN')">
				<th><mytaglib:sort-link column="customer_id"
						pageUrl="${baseUrl}">
						<i class="material-icons">description</i>
						customer
					</mytaglib:sort-link></th>

				<th><mytaglib:sort-link column="created" pageUrl="${baseUrl}">
						<i class="material-icons">access_time</i> created
					</mytaglib:sort-link></th>
			</sec:authorize>
			<th></th>
		</tr>
		<c:forEach var="route" items="${gridItem}" varStatus="loopCounter">
			<tr>
				<td><c:out value="${route.id}" /></td>
				<td><c:out value="${route.name}" /></td>
				<sec:authorize access="hasAnyRole('ADMIN')">
					<td><c:out value="${route.customerId}" /></td>
					<td><c:out value="${route.created}" /></td>
				</sec:authorize>
				<td class="right"><a class="btn-floating"
					href="${baseUrl}/${route.id}"><i class="material-icons">info</i></a>

					<sec:authorize access="!isAnonymous()">
						<c:if test="${(route.customerId==currentCustomer)}">
							<a class="btn-floating" href="${baseUrl}/${route.id}/edit"><i
								class="material-icons">edit</i></a>
							<a class="btn-floating red" href="${baseUrl}/${route.id}/delete"><i
								class="material-icons">delete</i></a>
						</c:if>
					</sec:authorize>
						<sec:authorize access="hasAnyRole('ADMIN')">

							<a class="btn-floating" href="${baseUrl}/${route.id}/edit"><i
								class="material-icons">edit</i></a>
							<a class="btn-floating red" href="${baseUrl}/${route.id}/delete"><i
								class="material-icons">delete</i></a>

						</sec:authorize>
					</td>

			</tr>
		</c:forEach>
	</tbody>
</table>
<jspFragments:paging />

<sec:authorize access="!isAnonymous()">

	<a class="waves-effect waves-light btn right" href="${baseUrl}/add"><i
		class="material-icons">add</i></a>
</sec:authorize>
