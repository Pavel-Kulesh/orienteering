<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<c:set var="baseUrl" value="${contextPath}/map" />
<h4 class="header"><mytaglib:i18n key="map.header" /></h4>
<table class="striped">
	<tbody>
		<tr>
			<th><mytaglib:sort-link column="id" pageUrl="${baseUrl}">id</mytaglib:sort-link></th>
			<th><mytaglib:sort-link column="name" pageUrl="${baseUrl}"><mytaglib:i18n key="map.name" /></mytaglib:sort-link></th>
			<sec:authorize access="hasAnyRole('ADMIN')">
				<th><mytaglib:sort-link column="customer_id"
						pageUrl="${baseUrl}"><mytaglib:i18n key="map.customerId" /></mytaglib:sort-link></th>
			</sec:authorize>
			<th></th>
		</tr>

		<c:forEach var="map" items="${gridItem}" varStatus="loopCounter">
			<tr>
				<td><c:out value="${map.id}" /></td>
				<td><c:out value="${map.name}" /></td>
				<sec:authorize access="hasRole('ADMIN')">
					<td><c:out value="${map.customerId}" /></td>
				</sec:authorize>
				<td class="right"><a class="btn-floating"
					href="${baseUrl}/${map.id}"><i class="material-icons">info</i></a>
					<sec:authorize access="hasRole('ADMIN')">
						<a class="btn-floating orange" href="${baseUrl}/${map.id}/edit"><i
							class="material-icons">edit</i></a>
						<a class="btn-floating red" href="${baseUrl}/${map.id}/delete"><i
							class="material-icons">delete</i></a>
					</sec:authorize></td>

			</tr>
		</c:forEach>
	</tbody>
</table>
<jspFragments:paging />
<sec:authorize access="hasRole('ADMIN')">

	<a class="waves-effect waves-light btn right" href="${baseUrl}/add"><i
		class="material-icons">add</i></a>
</sec:authorize>