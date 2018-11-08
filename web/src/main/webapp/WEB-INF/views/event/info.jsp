<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="baseUrl" value="${contextPath}/event" />
<h4 class="header">Info event</h4>


<div class="row">

	<div class="col s3">
		<form:form class="col s12" method="GET"
			action="${baseUrl}/list/event/id" modelAttribute="formModel"
			title="list of registered participants ">

			<a href="${contextPath}/list/event/${formModel.id}"
				class="btn waves-effect waves-light right" type="submit"><i
				class="large material-icons">home</i></a>

		</form:form>

	</div>
	<div class="col s9"></div>
</div>

<table class="bordered highlight">
	<tbody>
		<tr>


			<th><i class="material-icons">assignment_ind</i>name</th>

			<th>type</th>
			<th><i class="material-icons">access_time</i>date</th>
			<th><i class="material-icons">public</i>country</th>

			<th></th>

		</tr>

		<tr>
			<td><c:out value="${formModel.name}" /></td>

			<td><c:out value="${formModel.type }" /></td>
			<td><fmt:formatDate pattern="yyyy-MM-dd"
					value="${formModel.date}" /></td>


			<td><c:out value="${formModel.countryId}" /></td>




		</tr>

		<tr>
			<td colspan="4" align="justify">something</td>
		</tr>


		<tr>

			<td colspan="4" align="center">
				<table>
					<tbody>

						<c:out value="${formModel.info}" />
					</tbody>
				</table>
		</tr>

	</tbody>
</table>







<div class="row">
	<div class="col s9"></div>
	<div class="col s3">
		<a class="btn waves-effect waves-light right" href="${baseUrl}">Go
			backƒ<i class="material-icons right">undo</i>
		</a>
	</div>
</div>



</div>

