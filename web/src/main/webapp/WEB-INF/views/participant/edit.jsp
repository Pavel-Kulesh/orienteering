<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
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
		<sec:authorize access="hasRole('ADMIN')">
		<div class="row">
					<div class="input-field col s12">



						<form:select path="role">
							<option value="" disabled selected><mytaglib:i18n
									key="participant.select.role" />
								<form:options items="${roleChoices}" />
						</form:select>
						<form:errors path="role" cssClass="red-text" />
						<label for="type"><mytaglib:i18n key="participant.role" /></label>
					</div>
				</div>
		</sec:authorize>

		
		
		

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
		<a class="waves-effect waves-light btn red" href="${url}"><mytaglib:i18n
				key="participant.back" /><i class="material-icons right">undo</i> </a>
	</div>
		</div>

	</form:form>
</div>
