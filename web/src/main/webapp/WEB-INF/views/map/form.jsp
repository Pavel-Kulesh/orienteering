<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="baseUrl" value="${contextPath}/map" />
<h4 class="header">Edit Map</h4>
<div class="row">
	<form:form class="col s12" method="POST" action="${baseUrl}"
		modelAttribute="formModel">
		<form:input path="id" type="hidden" />

		<div class="row">
		<i class="material-icons">note</i>
			<div class="input-field col s12">
				<form:input path="name" type="text" disabled="${readonly}" />
				<form:errors path="name" cssClass="red-text" />
				<label for="name">Name</label>
			</div>
		</div>

		<table>
			<tr>
				<td><div class="row">
				<i class="material-icons">search</i>
						<div class="input-field col s12">
							<form:input path="latitude1" type="text" disabled="${readonly}" />
							<form:errors path="latitude1" cssClass="red-text" />
							<label for="latitude1">latitude1</label>
						</div>
					</div></td>
				<td>
					<div class="row">
					<i class="material-icons">search</i>
						<div class="input-field col s12">
							<form:input path="latitude2" type="text" disabled="${readonly}" />
							<form:errors path="latitude2" cssClass="red-text" />
							<label for="latitude2">latitude2</label>
						</div>
					</div>
				</td>
			</tr>


			<tr>
				<td><div class="row">
				<i class="material-icons">search</i>
						<div class="input-field col s12">
							<form:input path="longitude1" type="text" disabled="${readonly}" />
							<form:errors path="longitude1" cssClass="red-text" />
							<label for="longitude1">longitude1</label>
						</div>
					</div></td>
				<td>
					<div class="row">
					<i class="material-icons">search</i>
						<div class="input-field col s12">
							<form:input path="longitude2" type="text" disabled="${readonly}" />
							<form:errors path="longitude2" cssClass="red-text" />
							<label for="longitude2">longitude2</label>
						</div>
					</div>
				</td>
			</tr>


		</table>





		<div class="row">
			<div class="col s6"></div>
			<div class="col s3">
				<c:if test="${!readonly}">
					<button class="btn waves-effect waves-light right" type="submit">Save</button>
				</c:if>
			</div>
			<div class="col s3">
				<a class="btn waves-effect waves-light right" href="${baseUrl}">Go
					back to listƒ<i class="material-icons right"></i>
				</a>
			</div>
		</div>
	</form:form>
</div>
