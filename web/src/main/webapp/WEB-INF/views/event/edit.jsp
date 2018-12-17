<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<c:set var="baseUrl" value="${contextPath}/event" />
<h4 class="header">
	<mytaglib:i18n key="event.edit" />
</h4>

<div class="row">
	<form:form class="col s12" method="POST" action="${baseUrl}"
		modelAttribute="formModel">

		<form:input path="id" type="hidden" />
		<form:input path="version" type="hidden" />
		<form:input path="customerId" type="hidden" />


		<div class="row">
			<i class="material-icons">assignment_ind</i>
			<div class="input-field col s12">
				<form:input path="name" type="text" />
				<form:errors path="name" cssClass="red-text" />
				<label for="name"><mytaglib:i18n key="event.name" /></label>
			</div>
		</div>

		<div class="row">
			<div class="input-field col s12">
				<form:select path="type">
					<option value="" disabled selected><mytaglib:i18n
							key="event.select.type" />
						<form:options items="${typeChoices}" />
				</form:select>
				<form:errors path="type" cssClass="red-text" />
				<label for="type"><mytaglib:i18n key="event.type" /></label>
			</div>
		</div>

		<div class="row">
			<i class="material-icons">query_builder</i>
			<div class="input-field col s12">
				<form:input path="date" type="text" cssClass="datepicker" />
				<form:errors path="date" cssClass="red-text" />
				<label for="date"><mytaglib:i18n key="event.date" /></label>
			</div>


			<table>
				<tr>
					<td>
						<div class="row">
							<i class="material-icons">location_on</i>
							<div class="input-field col s12">
								<form:input path="latitude" type="text" />
								<form:errors path="latitude" cssClass="red-text" />
								<label for="latitude"><mytaglib:i18n key="event.lat" /></label>
							</div>
						</div>
					</td>
					<td><div class="row">
							<i class="material-icons">location_on</i>
							<div class="input-field col s12">
								<form:input path="longitude" type="text" />
								<form:errors path="longitude" cssClass="red-text" />
								<label for="longitude"><mytaglib:i18n key="event.long" /></label>
							</div>
						</div></td>
				</tr>
			</table>
		</div>


		<div class="row">
			<i class="material-icons">description</i>
			<div class="input-field col s12">

				<form:textarea style="height: 20%" path="info" type="text" />
				<form:errors path="info" cssClass="red-text" />
				<label for="info"><mytaglib:i18n key="event.info" /></label>
			</div>
		</div>

		<div class="row">
			<div class="col s6"></div>
			<div class="col s3">
				<button class="btn waves-effect waves-light right" type="submit">
					<mytaglib:i18n key="save" />
				</button>

			</div>
			<div class="col s3">
				<a class="btn waves-effect waves-light right red" href="${url}"><mytaglib:i18n key="event.back"/></a>
			</div>
		</div>
	</form:form>
</div>

