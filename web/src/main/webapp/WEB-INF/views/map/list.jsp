<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<c:set var="baseUrl" value="${contextPath}/map" />
<h4 class="header">Map</h4>
<style>
table {
	margin-bottom: 25px;
}
</style>
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
		<c:forEach var="map" items="${list}" varStatus="loopCounter">
			<tr>
				<td><c:out value="${map.id}" /></td>
				<td><c:out value="${map.name}" /></td>
				<td><c:out value="${map.userId}" /></td>
				<td></td>
				<td></td>
				<td></td>



				<td><fmt:formatDate pattern="yyyy-MM-dd" value="${map.created}" /></td>
				<td><fmt:formatDate pattern="yyyy-MM-dd" value="${map.updated}" /></td>
				<td class="right"><a class="btn-floating"
					href="${baseUrl}/${event.id}"><i class="material-icons">info</i></a>
					<a class="btn-floating" href="${baseUrl}/${map.id}/edit"><i
						class="material-icons">edit</i></a> <a
					class="btn-floating red disabled"
					href="${baseUrl}/${map.id}/delete"><i class="material-icons">delete</i></a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<a class="waves-effect waves-light btn right" href="${baseUrl}/add"><i
	class="material-icons">add</i></a>