<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<c:set var="baseUrl" value="${contextPath}/event" />
<h4 class="header">
	<mytaglib:i18n key="event.header" />
</h4>


<table class="bordered highlight">
	<tbody>
		<tr>
			<sec:authorize access="hasRole('ADMIN')">
				<th><mytaglib:sort-link column="id" pageUrl="${baseUrl}">
						<i class="material-icons">filter_vintage</i>id</mytaglib:sort-link></th>
			</sec:authorize>
			<th><mytaglib:sort-link column="name" pageUrl="${baseUrl}">
					<i class="material-icons">assignment_ind</i>
					<mytaglib:i18n key="event.name" />
				</mytaglib:sort-link></th>
			<th><mytaglib:sort-link column="type" pageUrl="${baseUrl}">
					<mytaglib:i18n key="event.type" />
				</mytaglib:sort-link></th>
			<th><mytaglib:sort-link column="date" pageUrl="${baseUrl}">
					<i class="material-icons">access_time</i>
					<mytaglib:i18n key="event.date" />
				</mytaglib:sort-link></th>
			<sec:authorize access="hasAnyRole('ADMIN','ORGANIZER')">
				<th><mytaglib:sort-link column="customer_id" pageUrl="${baseUrl}">
						<mytaglib:i18n key="event.customerId" />
					</mytaglib:sort-link></th>
			</sec:authorize>
			<th></th>
		</tr>
		<c:forEach var="event" items="${gridItem}" varStatus="loopCounter">
			<tr>
				<sec:authorize access="hasRole('ADMIN')">
					<td><c:out value="${event.id}" /></td>
				</sec:authorize>
				<td><c:out value="${event.name}" /></td>
				<td><c:out value="${event.type }" /></td>
				<td><fmt:formatDate pattern="yyyy-MM-dd" value="${event.date}" /></td>
				<sec:authorize access="hasAnyRole('ADMIN','ORGANIZER')">
					<td><c:out value="${event.customerId}" /></td>
				</sec:authorize>
				<td class="right"><a class="btn-floating" href="${baseUrl}/${event.id}"><i class="material-icons">info</i></a> <c:if test="${event.statusVisible}">
						<a class="btn-floating orange" href="${baseUrl}/edit/${event.id}"><i class="material-icons">edit</i></a>
						<a class="btn-floating red" href="${baseUrl}/delete/${event.id}"><i class="material-icons">delete</i></a>
					</c:if></td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<sec:authorize access="hasAnyRole('ADMIN','ORGANIZER')">
	<div class="fixed-action-btn">
		<a class="btn-floating btn-large right" href="${baseUrl}/add"> <i class="large material-icons">add</i>
		</a>
	</div>
</sec:authorize>

<script>
	$(document).ready(function() {
		$('.fixed-action-btn').floatingActionButton();
	});
</script>



<jspFragments:paging />
<sec:authorize access="hasAnyRole('ADMIN','ORGANIZER')">

	<a class="waves-effect waves-light btn right" href="${baseUrl}/add"><i class="material-icons">add</i> <mytaglib:i18n key="event.add" /></a>
</sec:authorize>