<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="baseUrl" value="${contextPath}/event" />
<h4 class="header">Edit Event</h4>
<div class="row">
	<form:form class="col s12" method="POST" action="${baseUrl}"
		modelAttribute="formModel">
		<form:input path="id" type="hidden" />

		<div class="row">
			<i class="material-icons">assignment_ind</i>
			<div class="input-field col s12">
				<form:input path="name" type="text" disabled="${readonly}" />
				<form:errors path="name" cssClass="red-text" />
				<label for="name">Name</label>
			</div>
		</div>

		<div class="row">
			<i class="material-icons">description</i>
			<div class="input-field col s12">
				<form:input path="info" type="text" disabled="${readonly}" />
				<form:errors path="info" cssClass="red-text" />
				<label for="info">Info</label>
			</div>
		</div>
		<form:input path="creatorId" type="hidden" />
		<form:input path="countryId" type="hidden" />


		<div class="row">
			<div class="input-field col s12">
				<form:select path="type" disabled="${readonly}">
					<form:options items="${typeChoices}" />
				</form:select>
				<form:errors path="type" cssClass="red-text" />
				<label for="type">type list</label>
			</div>
		</div>



		<div class="row">
			<i class="material-icons">query_builder</i>
			<div class="input-field col s12">
				<form:input path="date" type="text" disabled="${readonly}"
					cssClass="datepicker" />
				<form:errors path="date" cssClass="red-text" />
				<label for="date">date</label>
			</div>


			<table>
				<tr>
					<td>
						<div class="row">
							<i class="material-icons">location_on</i>
							<div class="input-field col s12">
								<form:input path="latitude" type="text" disabled="${readonly}" />
								<form:errors path="latitude" cssClass="red-text" />
								<label for="latitude">latitude</label>
							</div>
						</div>
					</td>
					<td><div class="row">
							<i class="material-icons">location_on</i>
							<div class="input-field col s12">
								<form:input path="longitude" type="text" disabled="${readonly}" />
								<form:errors path="longitude" cssClass="red-text" />
								<label for="longitude">longitude</label>
							</div>
						</div></td>
				</tr>

			</table>
			

		</div>

		<div class="row">
			<div class="col s6"></div>
			<div class="col s3">
				<c:if test="${!readonly}">
					<button class="btn waves-effect waves-light right" type="submit">Save</button>
				</c:if>
			</div>
			<div class="col s3">
				<a class="btn waves-effect waves-light right" href="${baseUrl}">Go
					backƒ<i class="material-icons right">undo</i>
				</a>
			</div>
		</div>
	</form:form>
</div>
