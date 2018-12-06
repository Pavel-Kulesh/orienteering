<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<c:set var="baseUrl" value="${contextPath}/participant" />
<h4 class="header">
	<mytaglib:i18n key="participant.edit" />
</h4>


<div class="row">
	<form:form class="col s12" method="POST" action="${baseUrl}"
		modelAttribute="formModel">
		<form:input path="id" type="hidden" />
		<form:input path="cityId" type="hidden" />


		<div class="row">
			<div class="input-field col s12">
				<form:input path="name" type="text" disabled="${readonly}" />
				<form:errors path="name" cssClass="red-text" />
				<label for="name"><mytaglib:i18n key="participant.name" /></label>
			</div>
		</div>

		<div class="row">
			<div class="input-field col s12">
				<form:input path="surname" type="text" disabled="${readonly}" />
				<form:errors path="surname" cssClass="red-text" />
				<label for="surname"><mytaglib:i18n
						key="participant.surname" /></label>
			</div>
		</div>

		<div class="row">
			<div class="input-field col s12">
				<form:input path="phone" type="text" disabled="${readonly}" />
				<form:errors path="phone" cssClass="red-text" />
				<label for="phone"><mytaglib:i18n key="participant.phone" /></label>
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
		<a class="waves-effect waves-light btn" href="${baseUrl}"><mytaglib:i18n
				key="participant.back" /><i class="material-icons right">undo</i> </a>
	</div>
		</div>

	</form:form>
</div>
