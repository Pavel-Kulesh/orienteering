<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<c:set var="baseUrl" value="${contextPath}/event" />
<h4 class="header"><mytaglib:i18n key="event.add.event"/></h4>
<div class="row">

	<div class="col s3"></div>
	<form:form class="col s6" method="POST" action="${baseUrl}"
		modelAttribute="formModel">

		<div class="row">
			<i class="material-icons">note</i>
			<div class="input-field col s12">
				<form:input path="name" type="text" />
				<form:errors path="name" cssClass="red-text" />
				<label for="name"><mytaglib:i18n key="event.name"/></label>
			</div>
		</div>

		<div class="row">
			<i class="material-icons">description</i>
			<div class="input-field col s12">
				<form:input path="info" type="text" />
				<form:errors path="info" cssClass="red-text" />
				<label for="info"><mytaglib:i18n key="event.info"/></label>
			</div>
		</div>


		<div class="row">
			<i class="material-icons">query_builder</i>
			<div class="input-field col s12">
				<form:input path="date" type="text" cssClass="datepicker" />
				<form:errors path="date" cssClass="red-text" />
				<label for="date"><mytaglib:i18n key="event.date"/></label>
			</div>

		</div>


		<div class="row">
			<div class="input-field col s12">
				<form:input path="customerId" type="hidden" />
			</div>
		</div>


		<div class="row">
			<i class="material-icons">filter</i>
			<div class="input-field col s12">
				<form:select path="countryId" >
					<option value="" disabled selected>Select country</option>
					<form:options items="${countryChoices}" />
				</form:select>
				<form:errors path="countryId" cssClass="red-text" />
				<label for="countryId"><mytaglib:i18n key="event.country"/></label>
			</div>
		</div>

		<div class="row">
			<i class="material-icons">filter</i>
			<form:select path="type" >
				<option value="" disabled selected>Select type</option>
				<form:options items="${typeChoices}" />
			</form:select>
			<form:errors path="type" cssClass="red-text" />
			<label for="type"><mytaglib:i18n key="event.type"/></label>
		</div>

		<table>
		<tr><th colspan="2" align="center"><mytaglib:i18n key="event.center"/></th></tr>
			<tr>
				<td><i class="material-icons">search</i>
					<div class="input-field col s12">
						<form:input path="latitude" type="text" />
						<form:errors path="latitude" cssClass="red-text" />
						<label for="latitude"><mytaglib:i18n key="event.lat"/></label>
					</div></td>
				<td><i class="material-icons">search</i>
					<div class="input-field col s12">
						<form:input path="longitude" type="text" />
						<form:errors path="longitude" cssClass="red-text" />
						<label for="longitude"><mytaglib:i18n key="event.long"/></label>
					</div></td>
			</tr>
		</table>

		<div class="row">
			<div class="col s6"></div>
			<div class="col s3">

				<button class="btn waves-effect waves-light right" type="submit"><mytaglib:i18n key="save"/></button>

			</div>
			<div class="col s3">
				<a class="btn waves-effect waves-light right" href="${baseUrl}"><mytaglib:i18n key="event.back"/>ƒ<i class="material-icons right"></i>
				</a>
			</div>
		</div>
	</form:form>
	<div class="col s3"></div>
</div>
