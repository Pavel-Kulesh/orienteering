<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<c:set var="baseUrl" value="${contextPath}/route" />

<div class="row center-align">
	<h4 class="header">
		<mytaglib:i18n key="route.add" />
	</h4>
	<div class="col s3"></div>
	<form:form class="col s6" method="POST" enctype="multipart/form-data" action="${baseUrl}" modelAttribute="formModel">

		<form:input path="id" type="hidden" />
		<form:input path="customerId" type="hidden" />

		<form:input path="name" type="text" />
		<form:errors path="name" cssClass="red-text" />
		<label for="name"><mytaglib:i18n key="route.name" /></label>

		<sec:authorize access="hasRole('ADMIN')">
			<form:select path="track">
				<option value="" disabled selected><mytaglib:i18n key="route.type" /></option>
				<form:options items="${wayChoices}" />
			</form:select>
			<label for="track"><mytaglib:i18n key="route.type" /></label>

		</sec:authorize>

		<table>
			<tr>
				<td><mytaglib:i18n key="route.file" /></td>
				<td><input type="file" name="fileDoc" /></td>
			</tr>

		</table>



		<div class="row">
			<div class="col s2"></div>
			<div class="col s3">
				<button class="btn waves-effect waves-light right" type="submit">Save</button>
			</div>
			<div class="col s3">
				<a class="btn waves-effect waves-light right red" href="${baseUrl}"><mytaglib:i18n key="route.back" /><i class="material-icons right"></i> </a>
			</div>
			<div class="col s4"></div>
		</div>
	</form:form>
	<div class="col s3"></div>
</div>
