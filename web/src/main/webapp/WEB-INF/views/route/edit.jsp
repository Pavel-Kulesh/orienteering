<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<c:set var="baseUrl" value="${contextPath}/route" />



<div class="row center-align">
	<h4 class="header">
		<mytaglib:i18n key="route.edit" />
	</h4>
	<div class="col s2"></div>
	<form:form class="col s8" method="POST" action="${baseUrl}/edit" modelAttribute="formModel">
		<form:input path="id" type="hidden" />
		<form:input path="customerId" type="hidden" />


		<div>
			<form:input path="name" type="text" />
			<form:errors path="name" cssClass="red-text" />
			<label for="name"><mytaglib:i18n key="route.name" /></label>
		</div>


		<sec:authorize access="hasRole('ADMIN')">
			<form:select path="track">
				<option value="" disabled selected><mytaglib:i18n key="route.type" /></option>
				<form:options items="${wayChoices}" />
			</form:select>
		</sec:authorize>



		<div class="row">
			<div class="col s2"></div>
			<div class="col s3">
				<button class="btn waves-effect waves-light right" type="submit">Save</button>
			</div>
			<div class="col s3">
				<a class="btn waves-effect waves-light right red" href="${baseUrl}" title="<mytaglib:i18n key="route.back" />"><mytaglib:i18n key="route.back" /><i class="material-icons right"></i> </a>
			</div>
		</div>
	</form:form>
	<div class="col s4"></div>
</div>
