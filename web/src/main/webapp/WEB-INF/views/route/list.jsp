<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<c:set var="baseUrl" value="${contextPath}/route" />
<h4 class="header">List routes</h4>

<input type="text" id="myInput" onkeyup="myFunction()"
	placeholder="Search for names (current page)..">


<table id="myTable" class="bordered highlight">
	<tbody>
		<tr>
			<th><mytaglib:sort-link column="name" pageUrl="${baseUrl}">
					<i class="material-icons">assignment_ind</i>
					Name
				</mytaglib:sort-link></th>
			<th><mytaglib:sort-link column="created" pageUrl="${baseUrl}">
					<i class="material-icons">access_time</i> created
					</mytaglib:sort-link></th>

			<sec:authorize access="hasAnyRole('ADMIN')">
				<th><mytaglib:sort-link column="customer_id"
						pageUrl="${baseUrl}">
						<i class="material-icons">description</i>
						customer
					</mytaglib:sort-link></th>
				<th><mytaglib:sort-link column="track" pageUrl="${baseUrl}">
						<i class="material-icons">description</i>
						way
					</mytaglib:sort-link></th>

			</sec:authorize>
			<th></th>
		</tr>
		<c:forEach var="route" items="${gridItem}" varStatus="loopCounter">
			<tr>

				<td><c:out value="${route.name}" /></td>
				<td><c:out value="${route.created}" /></td>
				<sec:authorize access="hasRole('ADMIN')">
					<td><c:out value="${route.customerId}" /></td>
					<td><c:out value="${route.track}" /></td>

				</sec:authorize>
				<td class="right"><a class="btn-floating"
					href="${baseUrl}/${route.id}"><i class="material-icons">info</i></a>
					<a class="btn-floating orange" href="${baseUrl}/${route.id}/edit"><i
						class="material-icons">edit</i></a> <a class="btn-floating red"
					href="${baseUrl}/${route.id}/delete"><i class="material-icons">delete</i></a>
			</tr>
		</c:forEach>
	</tbody>
</table>
<jspFragments:paging />

<sec:authorize access="!isAnonymous()">

	<a class="waves-effect waves-light btn right" href="${baseUrl}/add"><i
		class="material-icons">add</i></a>
</sec:authorize>






<script>
	function myFunction() {
		// Declare variables
		var input, filter, table, tr, td, i, txtValue;
		input = document.getElementById("myInput");
		filter = input.value.toUpperCase();
		table = document.getElementById("myTable");
		tr = table.getElementsByTagName("tr");

		// Loop through all table rows, and hide those who don't match the search query
		for (i = 0; i < tr.length; i++) {
			td = tr[i].getElementsByTagName("td")[0];
			if (td) {
				txtValue = td.textContent || td.innerText;
				if (txtValue.toUpperCase().indexOf(filter) > -1) {
					tr[i].style.display = "";
				} else {
					tr[i].style.display = "none";
				}
			}
		}
	}
</script>
