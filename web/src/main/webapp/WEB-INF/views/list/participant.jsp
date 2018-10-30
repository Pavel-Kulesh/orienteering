<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<c:set var="baseUrl" value="${contextPath}" />
<h4 class="header">List of participant</h4>
<table class="bordered highlight">
	<tbody>
		<tr>
			<th><i class="material-icons">filter_vintage</i>id</th>
			<th><i class="material-icons">assignment_ind</i>name</th>
			<th><i class="material-icons">assignment_ind</i>surname</th>
			<sec:authorize access="!isAnonymous()">
				<th><i class="material-icons">contact_phone</i>phone</th>
				<th><i class="material-icons">access_time</i>created</th>
				<th><i class="material-icons">access_time</i>updated</th>
				<th></th>
			</sec:authorize>
		</tr>
		<c:forEach var="customer" items="${gridItem}" varStatus="loopCounter">
			<tr>
				<td><c:out value="${customer.id}" /></td>
				<td><c:out value="${customer.name}" /></td>
				<td><c:out value="${customer.surname}" /></td>
				<sec:authorize access="!isAnonymous()">
					<td><c:out value="${customer.phone}" /></td>
					<td><fmt:formatDate pattern="yyyy-MM-dd"
							value="${customer.created}" /></td>
					<td><fmt:formatDate pattern="yyyy-MM-dd"
							value="${customer.updated}" /></td>
				</sec:authorize>
				<td class="right"><a class="btn-floating"
					href="${baseUrl}/participant/${customer.id}"><i
						class="material-icons">info</i></a> <sec:authorize
						access="hasAnyRole('ADMIN')">
						<a class="btn-floating" href="${baseUrl}/${customer.id}/edit"><i
							class="material-icons">edit</i></a>
						<a class="btn-floating red"
							href="${baseUrl}/${customer.id}/delete"><i
							class="material-icons">delete</i></a>
					</sec:authorize></td>

			</tr>
		</c:forEach>
	</tbody>
</table>

