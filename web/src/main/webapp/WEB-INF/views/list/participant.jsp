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
			<sec:authorize access="!isAnonymous()">
				<th><i class="material-icons">filter_vintage</i>id</th>
			</sec:authorize>
			<th><i class="material-icons">assignment_ind</i>name</th>
			<th><i class="material-icons">assignment_ind</i>surname</th>
			<th></th>
		</tr>
		<c:forEach var="customer" items="${gridItem}" varStatus="loopCounter">
			<tr>
				<sec:authorize access="!isAnonymous()">
					<td><c:out value="${customer.id}" /></td>
				</sec:authorize>
				<td><c:out value="${customer.name}" /></td>
				<td><c:out value="${customer.surname}" /></td>
				<td class="right"><a class="btn-floating"
					href="${baseUrl}/participant/${customer.id}"><i
						class="material-icons">info</i></a> <sec:authorize
						access="hasAnyRole('ADMIN')">
						<a class="btn-floating"
							href="${baseUrl}/participant/${customer.id}/edit"><i
							class="material-icons">edit</i></a>
						<a class="btn-floating red"
							href="${baseUrl}/participant/${customer.id}/delete"><i
							class="material-icons">delete</i></a>
					</sec:authorize></td>

			</tr>
		</c:forEach>
	</tbody>
</table>
<div class="row">
	<div class="col s5"></div>
	<div class="col s2">
		<a class="waves-effect waves-light btn" href="${url}"><mytaglib:i18n
				key="back" /><i class="material-icons right">undo</i> </a>
	</div>
	<div class="col s5"></div>
</div>
