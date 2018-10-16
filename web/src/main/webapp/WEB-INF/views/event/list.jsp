<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<c:set var="baseUrl" value="${contextPath}/event" />
<h4 class="header">Event</h4>
<table class="bordered highlight">
	<tbody>
		<tr>
			<th>id</th>
			<th>name</th>
			<th>info</th>
			<th>date</th>
			<th>creator_id</th>
			<th>country_id</th>
			<th>created</th>
			<th>updated</th>
			<th></th>
		</tr>
		<c:forEach var="event" items="${list}" varStatus="loopCounter">
			<tr>
				<td><c:out value="${event.id}" /></td>
				<td><c:out value="${event.name}" /></td>
				<td><c:out value="${event.info}" /></td>
				<td><fmt:formatDate pattern="yyyy-MM-dd" value="${event.date}" /></td>
				<td><c:out value="${event.creatorId}" /></td>
				<td><c:out value="${event.countryId}" /></td>

				<td><fmt:formatDate pattern="yyyy-MM-dd"
						value="${event.created}" /></td>
				<td><fmt:formatDate pattern="yyyy-MM-dd"
						value="${event.updated}" /></td>
				<td class="right"><a class="btn-floating"
					href="${baseUrl}/${event.id}"><i class="material-icons">info</i></a>
					<a class="btn-floating" href="${baseUrl}/${event.id}/edit"><i
						class="material-icons">edit</i></a> <a
					class="btn-floating red disabled"
					href="${baseUrl}/${event.id}/delete"><i class="material-icons">delete</i></a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<a class="waves-effect waves-light btn right" href="${baseUrl}/add"><i
	class="material-icons">add</i></a>