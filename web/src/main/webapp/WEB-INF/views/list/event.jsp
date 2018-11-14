<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<c:set var="baseUrl" value="${contextPath}" />
<h4 class="header">List of event</h4>
<table class="bordered highlight">
	<tbody>
		<tr>
			<th><i class="material-icons">filter_vintage</i>id</th>

			<th><i class="material-icons">assignment_ind</i>name</th>
			<th><i class="material-icons">access_time</i>date</th>
			<th>type</th>
		</tr>
		<c:forEach var="event" items="${gridItem}" varStatus="loopCounter">
			<tr>
				<td><c:out value="${event.id}" /></td>
				<td><c:out value="${event.name}" /></td>
				<td><fmt:formatDate pattern="yyyy-MM-dd" value="${event.date}" /></td>
				<td><c:out value="${event.type }" /></td>


				<td class="right"><a class="btn-floating"
					href="${baseUrl}/event/${event.id}" title="event info"><i
						class="material-icons">info</i></a> <sec:authorize
						access="!isAnonymous()">
						<a class="btn-floating" href="${baseUrl}/${event.id}/edit"><i
							class="material-icons">edit</i></a>
						<a class="btn-floating red" href="${baseUrl}/${event.id}/delete"><i
							class="material-icons">delete</i></a>
					</sec:authorize></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
