<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="baseUrl" value="${contextPath}/event" />
<h4 class="header">Add event</h4>
<div class="row">
	<form:form class="col s12" method="POST" action="${baseUrl}"
		modelAttribute="formModel">

		<div class="row">
			<i class="material-icons">note</i>
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


		<div class="row">
			<i class="material-icons">query_builder</i>
			<div class="input-field col s12">
				<form:input path="date" type="text" disabled="${readonly}"
					cssClass="datepicker" />
				<form:errors path="date" cssClass="red-text" />
				<label for="date">date</label>
			</div>

		</div>


		<div class="row">
			<div class="input-field col s12">
				<form:input path="customerId"  type="hidden" />
			</div>
		</div>


		<div class="row">
			<i class="material-icons">filter</i>
			<div class="input-field col s12">
				<form:select path="countryId" disabled="${readonly}">
					<option value="" disabled selected>Select country</option>
					<form:options items="${countryChoices}" />
				</form:select>
				<form:errors path="countryId" cssClass="red-text" />
				<label for="countryId">country</label>
			</div>
		</div>

		<div class="row">
			<i class="material-icons">filter</i>
			<form:select path="type" disabled="${readonly}">
				<option value="" disabled selected>Select type</option>
				<form:options items="${typeChoices}" />
			</form:select>
			<form:errors path="type" cssClass="red-text" />
			<label for="type">type list</label>
		</div>

		<table>
			<tr>
				<td><i class="material-icons">search</i>
					<div class="input-field col s12">
						<form:input path="latitude" type="text" disabled="${readonly}" />
						<form:errors path="latitude" cssClass="red-text" />
						<label for="latitude">latitude</label>
					</div></td>
				<td><i class="material-icons">search</i>
					<div class="input-field col s12">
						<form:input path="longitude" type="text" disabled="${readonly}" />
						<form:errors path="longitude" cssClass="red-text" />
						<label for="longitude">longitude</label>
					</div></td>
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
