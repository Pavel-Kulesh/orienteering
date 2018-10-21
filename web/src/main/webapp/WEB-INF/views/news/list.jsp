<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>

<c:set var="baseUrl" value="${contextPath}/news" />
<h4 class="header">News</h4>
<table class="bordered highlight">
	<tbody>
		<tr>
			<th><mytaglib:sort-link column="id" pageUrl="${baseUrl}">id</mytaglib:sort-link></th>
			<th><mytaglib:sort-link column="name" pageUrl="${baseUrl}">name</mytaglib:sort-link></th>
			<th><mytaglib:sort-link column="info" pageUrl="${baseUrl}">info</mytaglib:sort-link></th>
			<th>created</th>
			<th>updated</th>
			<th></th>
		</tr>
		<c:forEach var="news" items="${listDTO.list}" varStatus="loopCounter">
			<tr>
				<td><c:out value="${news.id}" /></td>
				<td><c:out value="${news.name}" /></td>
				<td><c:out value="${news.info}" /></td>
				<td><fmt:formatDate pattern="yyyy-MM-dd"
						value="${news.created}" /></td>
				<td><fmt:formatDate pattern="yyyy-MM-dd"
						value="${news.updated}" /></td>
				<td class="right"><a class="btn-floating"
					href="${baseUrl}/${news.id}"><i class="material-icons">info</i></a>
					<a class="btn-floating" href="${baseUrl}/${news.id}/edit"><i
						class="material-icons">edit</i></a> <a class="btn-floating red"
					href="${baseUrl}/${news.id}/delete"><i class="material-icons">delete</i></a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<jspFragments:paging />
<a class="waves-effect waves-light btn right" href="${baseUrl}/add"><i
	class="material-icons">add</i></a>
